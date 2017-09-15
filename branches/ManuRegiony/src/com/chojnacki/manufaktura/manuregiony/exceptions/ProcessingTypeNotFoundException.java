package com.chojnacki.manufaktura.manuregiony.exceptions;

/**
 * Exception thrown during data reading
 */
public class ProcessingTypeNotFoundException extends RuntimeException {
    public ProcessingTypeNotFoundException(String message, Exception e) {
        super(message, e);
    }
}
