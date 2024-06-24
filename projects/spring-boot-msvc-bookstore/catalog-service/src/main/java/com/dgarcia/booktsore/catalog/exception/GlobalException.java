package com.dgarcia.booktsore.catalog.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler{

   
    private Logger logger = LoggerFactory.getLogger(GlobalException.class);


    @ExceptionHandler(ResourceNotFound.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseExceptionDTO> handleResourceNotFoudn(ResourceNotFound ex){
        logger.info("HandleResource Not Found : {}", ex.getMessage());
        ResponseExceptionDTO dto = new ResponseExceptionDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.ok(dto);
    }

    @ExceptionHandler(ResourceFound.class)
    @ResponseStatus(value = HttpStatus.FOUND)
    public ResponseEntity<ResponseExceptionDTO> handleResourceFound(ResourceFound ex){
      logger.info("HandleResounserFound Exception  {}", ex.getMessage());
      ResponseExceptionDTO dto = new ResponseExceptionDTO( ex.getMessage(), HttpStatus.FOUND.value());
      return ResponseEntity.ok(dto);
    }
    
    
}
