package com.dgarcia.booktsore.catalog.exception;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND)
public class ResourceFound extends RuntimeException{

    public ResourceFound(String message){
        super(message);
    }

}
