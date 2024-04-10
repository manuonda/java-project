package com.springboot.blog.exception;


import com.springboot.blog.payload.ResponseExceptionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * Controller Advice: Responsable of captura exception the manera global
 */
@RestControllerAdvice
public class CustomExceptionHandler {


    private static Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);


    @ExceptionHandler(EntityFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseExceptionDTO> handlerEntityFound(EntityFoundException ex){
      ResponseExceptionDTO dto = new ResponseExceptionDTO(ex.getMessage(), HttpStatus.OK.value());
      return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @ExceptionHandler(EntityFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseExceptionDTO> handlerEntityNotFound(EntityNotFoundException ex){
        ResponseExceptionDTO dto  = new ResponseExceptionDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.ok(dto);
    }


}
