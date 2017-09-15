package com.chojnacki.manufaktura.manuregiony.processing;

import com.chojnacki.manufaktura.manuregiony.gui.Main;
import com.chojnacki.manufaktura.manuregiony.model.Regions;
import javafx.concurrent.Task;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

/**
 * The class produces report and stores it in file.
 */
public class ReportStore extends Task<String> {
    private static final Logger LOGGER = LogManager.getLogger(ReportStore.class);
    private final Main main;
    private Timer timer = new Timer();
    private String outputPath;

    public ReportStore(Main main) {
        updateTitle("Tworzenie raportu.");
        this.main = main;
    }

    public static String getMessage(Collection<CSVRecord> inputErrors) {
        LOGGER.log(Level.DEBUG, "Creating report from input errors {}", inputErrors.size());
        StringBuilder sb = new StringBuilder();
        if (!inputErrors.isEmpty()) {
            sb.append("Podczas przetwarzania pliku wejściowego napotkano błędy przy wpisach:").append("\n");
            printCars(inputErrors, sb);
        }
        if (Regions.OUTERN_POLAND.getObjectsNumber() != 0) {
            sb.append("Dla następujących pojazdów nie znaleziono regionu:").append("\n");
            printCars(Regions.OUTERN_POLAND.getCars(), sb);
        }
        return sb.toString();
    }

    public static void printCars(Collection cars, StringBuilder sb) {
        for (Object inputError : cars) {
            sb.append(inputError.toString()).append("\n");
        }
    }

    @Override
    protected String call() throws Exception {
        timer.start();
        updateMessage("Tworzenie raportu...");
        String message = getMessage(main.getInputErrors());
        if (message.length() > 10000) {
            try {
                File reportFile = storeToFile(message);
                message = "Wyświetlanie raportu może zająć zbyt dużo czasu.\nZostał on zapisany do pliku:\n"
                        + reportFile
                        + "\nPoniżej jest zaprezentowany jest tylko fragment:\n" + message.substring(0, 10000) + "...";
                updateMessage("Raport zapisany w czasie " + timer.stopAndReturn() + " sekund");
            } catch (Exception e) {
                updateMessage("Błąd podczas zapisywania raportu");
                setException(e);
                throw e;
            }
        } else {
            updateMessage("Raport utworzono pomyślnie w czasie " + timer.stopAndReturn() + " sekund");
        }
        updateProgress(0,1);
        return message;
    }

    private File storeToFile(String message) throws IOException {
        File reportFile = new File(outputPath + File.separator + "raport.txt");
        LOGGER.log(Level.DEBUG, "Too many errors to present in the dialog. Storing to file {}", reportFile.getAbsolutePath());
        FileOutputStream fos = new FileOutputStream(reportFile);
        fos.write(message.getBytes());
        fos.close();
        return reportFile;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }
}
