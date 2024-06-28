package com.dgarcia.bookstore.orders.exception;

public class ResourceNotFound extends RuntimeException{

    ResourceNotFound(String message){
        super(message);
    }
}
