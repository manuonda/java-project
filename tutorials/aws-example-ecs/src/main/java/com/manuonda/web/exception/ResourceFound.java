package com.manuonda.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceFound extends RuntimeException{

    public ResourceFound(String message){
        super(message);
    }
}
