package com.chojnacki.manufaktura.manuregiony.exceptions;

/**
 * Exception thrown during processing cars, when region car belongs to is not found.
 */
public class RegionNotFoundException extends RuntimeException {
    public RegionNotFoundException(String regionSymbol) {
        super("Region with symbol " + regionSymbol + " not found.");
    }
}
