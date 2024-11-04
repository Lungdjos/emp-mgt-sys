package com.radix.ems.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.List;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceAlreadyExistException extends RuntimeException {

    public ResourceAlreadyExistException(String message) {
        super(message);
    }

    public List<String> getErrors() {
        return Collections.singletonList(getMessage());
    }
}
