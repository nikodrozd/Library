package com.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ResourceIsAssignedException extends RuntimeException{

    public ResourceIsAssignedException(String message) {
        super(message);
    }
}
