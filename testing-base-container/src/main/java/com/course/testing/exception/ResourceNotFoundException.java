package com.course.testing.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message){
       super(message);
    }


    public ResourceNotFoundException(String message, Throwable cause){
        super(message, cause);
    }


}
