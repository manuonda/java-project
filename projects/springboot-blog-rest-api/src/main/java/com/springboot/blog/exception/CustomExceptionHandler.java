package com.springboot.blog.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {


    private static Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);


}
