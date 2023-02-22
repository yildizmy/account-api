package com.github.yildizmy.exception;

/**
 * Custom exception class used when no record is returned for the given parameters
 */
public class NoSuchElementFoundException extends RuntimeException {

    public NoSuchElementFoundException() {
        super();
    }

    public NoSuchElementFoundException(String message) {
        super(message);
    }

    public NoSuchElementFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
