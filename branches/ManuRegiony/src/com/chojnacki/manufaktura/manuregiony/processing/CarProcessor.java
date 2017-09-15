package com.chojnacki.manufaktura.manuregiony.processing;

import com.chojnacki.manufaktura.manuregiony.exceptions.RegionNotFoundException;
import com.chojnacki.manufaktura.manuregiony.input.CSVDataReader;
import com.chojnacki.manufaktura.manuregiony.model.Car;
import com.chojnacki.manufaktura.manuregiony.model.ProcessingType;
import com.chojnacki.manufaktura.manuregiony.model.Region;
import com.chojnacki.manufaktura.manuregiony.model.Regions;
import javafx.concurrent.Task;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.List;

/**
 * Task which processes all the cars.
 */
public class CarProcessor extends Task<Void> {

    private static final Logger LOGGER = LogManager
            .getLogger(CarProcessor.class);
    private final CSVDataReader reader;
    private Timer timer = new Timer();

    public CarProcessor(CSVDataReader reader) {
        updateTitle("Przypisywanie obiektów wejściowych do regionów.");
        this.reader = reader;
    }

    private List<Region> processObject(Car object) {
        String regionSymbol = object.getPlate().getRegion();
        return Regions.getInstance().getRegionByPlate(regionSymbol, object.getOrigin());
    }

    private void processCollection(Collection<Car> collection)
            throws InterruptedException {
        updateMessage("Przetwarzanie danych...");
        double counter = 0.0;
        double collectionSize = collection.size();
        boolean errors = false;
        for (Car car : collection) {
            LOGGER.log(Level.TRACE, "Processing car {}: " + car.toString());
            counter += 1.0;
            updateProgress(counter, collectionSize);
            try {
                List<Region> regions = processObject(car);
                boolean isLdz = false;
                for (Region region : regions) {
                    region.increment();
                    if (region.isLodz()) {
                        isLdz = true;
                    }
                }
                Regions.ALL.increment();
                if (!isLdz) {
                    Regions.OUTERN_LDZ.increment();
                }
            } catch (RegionNotFoundException regionNotFoundError) {
                errors = true;
                Regions.OUTERN_POLAND.increment(car);
                LOGGER.log(Level.TRACE,
                        "Region not found for car: " + car.toString());
            }
            if (Thread.interrupted()) {
                InterruptedException exc = new InterruptedException(
                        "Thread has been cancelled");
                setException(exc);
                throw exc;
            }
        }
        if (errors) {
            updateMessage("Dane przetworzone z błędami w czasie " + timer.stopAndReturn() + " sekund");
        } else {
            updateMessage("Dane przetworzone w czasie " + timer.stopAndReturn() + " sekund");
        }
        updateProgress(0, 1);
    }

    @Override
    protected Void call() throws Exception {
        timer.start();
        try {
            processCollection(reader.get());
        } catch (Exception exc) {
            updateProgress(0, 1);
            setException(exc.getCause());
            updateMessage("Błąd przetwarzania");
            throw exc;
        }
        return null;
    }
}
