package com.capgemini.exception;

/**
 * Custom exception class used when record is already exists for the given parameters
 */
public class ElementAlreadyExistsException extends RuntimeException {

    public ElementAlreadyExistsException() {
        super();
    }

    public ElementAlreadyExistsException(String message) {
        super(message);
    }

    public ElementAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
