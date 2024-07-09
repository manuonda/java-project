package com.dgarcia.bookstore.orders.exception;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

  private static final URI NOT_FOUND_TYPE = URI.create("https://api.bookstore.com/errors/not-found");
  private static final URI ISE_FOUND_TYPE = URI.create("https://api.bookstore.com/errors/server-error");
  private static final URI BAD_REQUEST_TYPE = URI.create("https://api.bookstore.com/errors/bad-request");
  private static final String SERVICE_NAME = "order-service";
    

    /**
     *Logger 
     */
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(ResourceNotFound.class)
    ResponseEntity<ProblemDetail> handleResourceNotFound(ResourceNotFound e){
      logger.info("HandleResource Not Found : {}", e.getMessage());
      StringWriter sw = new StringWriter();
      //PrintWriter pw = new PrintWriter(sw);
      //e.printStackTrace(pw);
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

    @ExceptionHandler(InvalidOrderException.class)
    ProblemDetail handleInvalidOrderException(InvalidOrderException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("Invalid Order Creation Request");
        problemDetail.setType(BAD_REQUEST_TYPE);
        problemDetail.setProperty("service", SERVICE_NAME);
        problemDetail.setProperty("error_category", "Generic");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }


    @Override
    @Nullable 
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Invalid request payload");
        problemDetail.setTitle("Bad Request");
        problemDetail.setType(BAD_REQUEST_TYPE);
        problemDetail.setProperty("errors", errors);
        problemDetail.setProperty("service", SERVICE_NAME);
        problemDetail.setProperty("error_category", "Generic");
        problemDetail.setProperty("timestamp", Instant.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }
}
