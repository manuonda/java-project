package com.springboot.blog.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogApiException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;  

    public BlogApiException(String message){
      super(message);
    }

    public BlogApiException(HttpStatus status, String message){
      this.httpStatus = status;
      this.message = message;

    }
}
