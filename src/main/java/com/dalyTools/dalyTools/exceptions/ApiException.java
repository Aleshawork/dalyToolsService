package com.dalyTools.dalyTools.exceptions;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {


    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;


    public ApiException(String message, Throwable throwable, HttpStatus httpStatus) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }
}
