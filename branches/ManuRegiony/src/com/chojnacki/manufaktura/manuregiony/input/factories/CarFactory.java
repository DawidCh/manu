package com.chojnacki.manufaktura.manuregiony.input.factories;

import com.chojnacki.manufaktura.manuregiony.model.Car;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Factory for creating cars from records
 */
public class CarFactory {
    private final static Logger LOGGER = LogManager.getLogger(CarFactory.class);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private CarFactory() {
    }

    public static Car assembleCar(CSVRecord record) throws ParseException {
        return new Car(record.getRecordNumber(),
                record.get(14), record.get(15),
                sdf.parse(record.get(1) + "-" + record.get(2) + "-" + record.get(3) + " " + record.get(4) + ":" + record.get(5) + ":" + record.get(6) + ":" + record.get(7)),
                PlateFactory.assemplePlate(record.get(13)),
                new Locale(record.get(11)));
    }
}
