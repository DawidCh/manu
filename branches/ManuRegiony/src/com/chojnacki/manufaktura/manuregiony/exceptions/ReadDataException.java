package com.chojnacki.manufaktura.manuregiony.exceptions;

/**
 * Exception thrown during data reading
 */
public class ReadDataException extends RuntimeException {
    public ReadDataException(String message, Exception e) {
        super(message, e);
    }
}
