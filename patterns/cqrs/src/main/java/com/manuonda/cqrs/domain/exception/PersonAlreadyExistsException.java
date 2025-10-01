package com.manuonda.cqrs.domain.exception;

public class PersonAlreadyExistsException extends RuntimeException {
    public PersonAlreadyExistsException(String email) {
        super("Person with email " + email + " already exists");
    }
}
