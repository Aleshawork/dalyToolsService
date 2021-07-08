package com.dalyTools.dalyTools.exceptions;

public abstract class ApiException extends RuntimeException {

    public ApiException() {
    }

    public ApiException(String message) {
        super(message);
    }
}
