package com.epam.mentoring.rest.controllers;

import com.epam.mentoring.rest.error.CannotSaveException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CannotSaveException.class)
    public ResponseEntity<Object> handleCannotSaveException(CannotSaveException ex,
                                                            WebRequest request) {
        String responseBody = String.format("{'error' : 'Can not save object', 'message':'%s'}",
                ex.getMessage().replace("'", "\\'"));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(ex, responseBody, httpHeaders,
               HttpStatus.BAD_REQUEST, request );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        String responseBody = String.format("{'error': 'Object validation failed', 'message':'%s'}",
                exception.getMessage().replace("'", "\\'"));
        return super.handleExceptionInternal(exception, responseBody, headers, status, request);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleDataAcessException(Exception exception, WebRequest request) {
        String responseBody = String.format("{'error': 'Can not get data', 'message': '%s'}",
                exception.getMessage().replace("'", "\\'"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(exception, responseBody, headers,
                HttpStatus.BAD_REQUEST, request);
    }

}
