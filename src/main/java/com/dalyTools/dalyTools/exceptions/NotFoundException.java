package com.dalyTools.dalyTools.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException  extends ApiClientException {


    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }


}
