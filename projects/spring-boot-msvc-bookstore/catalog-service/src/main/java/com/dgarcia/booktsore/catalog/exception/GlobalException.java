package com.dgarcia.booktsore.catalog.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxProperties.Problemdetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler{

    private static final URI NOT_FOUND_TYPE = URI.create("https://api.bookstore.com/errors/not-found");
    private static final URI ISE_FOUND_TYPE = URI.create("https://api.bookstore.com/errors/server-error");
   
    /**
     *Logger 
     */
    private Logger logger = LoggerFactory.getLogger(GlobalException.class);


    // @ExceptionHandler(ResourceNotFound.class)
    // @ResponseStatus(value = HttpStatus.NOT_FOUND)
    // public ResponseEntity<ResponseExceptionDTO> handleResourceNotFoudn(ResourceNotFound ex){
    //     logger.info("HandleResource Not Found : {}", ex.getMessage());
    //     ResponseExceptionDTO dto = new ResponseExceptionDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    //     return ResponseEntity.ok(dto);
    // }

    // @ExceptionHandler(ResourceFound.class)
    // @ResponseStatus(value = HttpStatus.FOUND)
    // public ResponseEntity<ResponseExceptionDTO> handleResourceFound(ResourceFound ex){
    //   logger.info("HandleResounserFound Exception  {}", ex.getMessage());
    //   ResponseExceptionDTO dto = new ResponseExceptionDTO( ex.getMessage(), HttpStatus.FOUND.value());
    //   return ResponseEntity.ok(dto);
    // }
    

    @ExceptionHandler(ResourceNotFound.class)
    ResponseEntity<ProblemDetail> handleResourceNotFound(ResourceNotFound e){
      logger.info("HandleResource Not Found : {}", e.getMessage());
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      e.printStackTrace(pw);
      ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, 
      sw.toString());
      problemDetail.setTitle(e.getMessage());
      problemDetail.setProperty("timestamp", Instant.now());
      problemDetail.setType(NOT_FOUND_TYPE);

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }

    @ExceptionHandler(ResourceFound.class)
    ResponseEntity<ProblemDetail> handleResourceFound(ResourceFound e){
      logger.info("HandleResource  Found : {}", e.getMessage());
      ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
      problemDetail.setTitle(e.getMessage());
      problemDetail.setProperty("timestamp", Instant.now());
      problemDetail.setType(ISE_FOUND_TYPE);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }
    
}
