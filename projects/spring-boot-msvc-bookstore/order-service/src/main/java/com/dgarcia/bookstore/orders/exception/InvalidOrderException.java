package com.dgarcia.bookstore.orders.exception;


public class InvalidOrderException extends RuntimeException{
    public InvalidOrderException(String message){
        super(message);
    }
}
