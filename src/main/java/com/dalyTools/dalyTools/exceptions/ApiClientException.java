package com.dalyTools.dalyTools.exceptions;

public abstract  class ApiClientException extends ApiException {
    public ApiClientException() {
    }

    public ApiClientException(String message) {
        super(message);
    }
}
