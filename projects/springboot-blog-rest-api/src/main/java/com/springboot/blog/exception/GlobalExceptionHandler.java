package com.springboot.blog.exception;


import com.springboot.blog.payload.ResponseExceptionDTO;

import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * Controller Advice: Responsable of captura exception the manera global
 */
@RestControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler{


    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    //handel specific exceptions
    @ExceptionHandler(EntityFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseExceptionDTO> handlerEntityFound(EntityFoundException ex){
      logger.info("ExceptionEntityFound : ".concat(ex.getMessage()));
      ResponseExceptionDTO dto = new ResponseExceptionDTO(ex.getMessage(), HttpStatus.OK.value());
      return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    //global exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseExceptionDTO> handleGlobalException(Exception ex){
      ResponseExceptionDTO dto = new ResponseExceptionDTO(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dto);
    }

    @ExceptionHandler(ResourceNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseExceptionDTO> handlerEntityNotFound(ResourceNotFound ex){
        logger.info("handlerEntityNotFound : ".concat(ex.getMessage()));
        ResponseExceptionDTO dto  = new ResponseExceptionDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.ok(dto);
    }

    @ExceptionHandler(BlogApiException.class)
    public ResponseEntity<ResponseExceptionDTO> handleGlobalException(BlogApiException ex){
      ResponseExceptionDTO dto  = new ResponseExceptionDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
      return ResponseEntity.ok(dto);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ResponseExceptionDTO> handleAccessDeniedException(AccessDeniedException ex){
        ResponseExceptionDTO dto = new ResponseExceptionDTO(ex.getMessage(), HttpStatus.UNAUTHORIZED.value());
         return ResponseEntity.ok(dto);
    }


    @ExceptionHandler(PaymentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseExceptionDTO> handEntityPaymentException(PaymentException ex){
        ResponseExceptionDTO dto = new ResponseExceptionDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.ok(dto);
    }



}
