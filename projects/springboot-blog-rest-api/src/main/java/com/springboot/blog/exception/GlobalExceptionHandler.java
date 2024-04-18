package com.springboot.blog.exception;

public class GlobalExceptionHandler extends RuntimeException{
    public GlobalExceptionHandler(String message){
      super(message);
    }
}
