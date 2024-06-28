package com.dgarcia.bookstore.orders.exception;

public class ResourceFound extends RuntimeException{

    ResourceFound(String message){
        super(message);
    }
}
