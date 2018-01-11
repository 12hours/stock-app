package com.epam.mentoring.web.client.exception;

public class ServerDataAccessException extends RuntimeException {
    public ServerDataAccessException(String message) {
        super(message);
    }

    public ServerDataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
