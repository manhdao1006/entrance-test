package com.haibazo.entrance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class ResourceNotFormatException extends RuntimeException {

    public ResourceNotFormatException(String message) {
        super(message);
    }
}
