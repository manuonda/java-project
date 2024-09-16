package com.manuonda.web.exception;

import java.time.Instant;

import org.springframework.boot.autoconfigure.web.reactive.WebFluxProperties.Problemdetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import java.net.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler  extends RuntimeException{

    private static final  URI NOT_FOUND_TYPE="https://api.bookstore.com/errors/not-found";
    private static final  URI FOUND_TYPE="https://api.bookstore.com/errors/not-found";



    @ExceptionHandler(ResourceFound.class)
    public ResponseEntity<ProblemDetail> handleResourceFound( ResourceFound e){
     log.info("handleResourceFoudn : {}",e.getMessage());
     ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.FOUND, e.getMessage());
     detail.setTitle(e.getMessage());
     detail.setProperty("timestamp", Instant.now());
     detail.setProperty("message", e.getMessage());
     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detail);
    }


    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ProblemDetail> handleNotFound(ResourceNotFound e){
        log.info("handleResourceNotFound : {}", e.getMessage());
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle(e.getMessage());
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setType(null);
    }
    
    



}
