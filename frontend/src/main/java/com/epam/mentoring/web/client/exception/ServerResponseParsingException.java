package com.epam.mentoring.web.client.exception;

/**
 * More specific type of ServerDataAccessException
 * <p>
 * Supposed to be thrown during server response deserialization.
 * Basically it means that we can not be sure whether operation was completed on server side so further checking is required
 */
public class ServerResponseParsingException extends ServerDataAccessException{
    public ServerResponseParsingException(String message) {
        super(message);
    }

    public ServerResponseParsingException(String message, Throwable cause) {
        super(message, cause);
    }
}
