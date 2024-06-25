package com.dgarcia.booktsore.catalog.exception;

public record ResponseExceptionDTO(
    String message, int status
){}
