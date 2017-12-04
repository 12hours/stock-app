package com.epam.mentoring.rest.error;

public class CannotSaveException extends RuntimeException {
    public CannotSaveException(String message) {
        super(message);
    }

    public CannotSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
