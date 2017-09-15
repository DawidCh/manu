package com.chojnacki.manufaktura.manuregiony.exceptions;

import com.chojnacki.manufaktura.manuregiony.model.Region;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Exception thrown when there are two regions with the same region shortcut.
 */
public class DuplicatedRegion extends RuntimeException {

    private static final Logger LOGGER = LogManager.getLogger(DuplicatedRegion.class);
    public DuplicatedRegion(Region oldOne, Region region) {
        LOGGER.log(Level.FATAL, "There is already region with the shortcuts {}. Conflicted regions {} and {}.", region.getRegionShortcutsAsText(), region.getName(), oldOne.getName());
    }
}
