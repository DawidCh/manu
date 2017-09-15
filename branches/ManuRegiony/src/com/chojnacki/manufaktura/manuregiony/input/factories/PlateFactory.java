package com.chojnacki.manufaktura.manuregiony.input.factories;

import com.chojnacki.manufaktura.manuregiony.model.Plate;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

/**
 * Factory for #{@link Plate} creation from cell content
 */
public class PlateFactory {
    private final static Logger LOGGER = LogManager.getLogger(PlateFactory.class);

    public static Plate assemplePlate(String cellValue) {
        if (!isPlateValid(cellValue)) {
            LOGGER.log(Level.TRACE, "Plate number is not valid: " + cellValue);
            cellValue = "--not-- --valid--";
        }
        return new Plate(cellValue);
    }

    private static boolean isPlateValid(String number) {
        return Strings.isNotBlank(number) && number.split(" ").length == 2;
    }
}
