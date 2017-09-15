package com.chojnacki.manufaktura.manuregiony.input;

import com.chojnacki.manufaktura.manuregiony.exceptions.ReadDataException;
import com.chojnacki.manufaktura.manuregiony.gui.Main;
import com.chojnacki.manufaktura.manuregiony.model.Car;
import com.chojnacki.manufaktura.manuregiony.processing.Timer;
import javafx.concurrent.Task;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.chojnacki.manufaktura.manuregiony.input.factories.CarFactory.assembleCar;

/**
 * Task responsible for reading data from input file
 */
public class CSVDataReader extends Task<Set<Car>> {
    private final Collection<CSVRecord> inputErrors;
    private List<File> input;
    private final static Logger LOGGER = LogManager.getLogger(CSVDataReader.class);
    private Timer timer = new Timer();

    public CSVDataReader(Main main) {
        updateTitle("Wczytywanie danych z pliku wejściowego.");
        this.inputErrors = main.getInputErrors();
    }

    private Set<Car> readData() throws InterruptedException {
        Set<Car> result = new HashSet<>();
        try {
            FileReader reader;
            for (File file : input) {
                updateMessage("Wczytywanie danych z pliku " + file.getName());
                LOGGER.log(Level.DEBUG, "Processing file {}", file.getAbsolutePath());
                reader = new FileReader(file);
                CSVParser parser = CSVFormat.DEFAULT.withDelimiter(';').withQuote(null).parse(reader);
                List<CSVRecord> records = parser.getRecords();
                double recordsSize = records.size();
                for (int i = 1; i < recordsSize; i++) {
                    updateProgress((double) i, recordsSize);
                    CSVRecord record = records.get(i);
                    try {
                        Car car = assembleCar(record);
                        result.add(car);
                    } catch (ParseException exc) {
                        inputErrors.add(record);
                        LOGGER.log(Level.ERROR, "Error while assembling car for entry: {}", record.toString(), exc);
                    } catch (Exception exc) {
                        LOGGER.log(Level.DEBUG, "Record read: " + record.toString());
                        throw exc;
                    }
                    if (Thread.interrupted()) {
                        throw new InterruptedException("Thread has been cancelled");
                    }
                }
            }
            updateMessage("Dane wczytane w czasie " + timer.stopAndReturn() + " sekund");
        } catch (InterruptedException exc) {
            setException(exc);
            throw exc;
        } catch (Exception e) {
            updateMessage("Błąd podczas wczytywania danych");
            ReadDataException exc = new ReadDataException("Exception during reading", e);
            setException(exc);
            throw exc;
        } finally {
            updateProgress(0, 1);
        }
        return result;
    }

    @Override
    public Set<Car> call() throws Exception {
        timer.start();
        Set<Car> cars = readData();
        timer.stop();
        return cars;
    }

    public void setInput(List<File> input) {
        this.input = input;
    }
}
