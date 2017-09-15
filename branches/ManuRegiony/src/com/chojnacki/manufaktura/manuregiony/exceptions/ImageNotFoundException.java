package com.chojnacki.manufaktura.manuregiony.exceptions;

/**
 * Exception thrown when image is not found.
 */
public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException(String s) {
        super(s);
    }
}
