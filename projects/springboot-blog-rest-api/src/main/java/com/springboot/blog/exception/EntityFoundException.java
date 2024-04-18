package com.springboot.blog.exception;




public class EntityFoundException extends RuntimeException{

    public EntityFoundException(String message){
        super(message);
    }
}
