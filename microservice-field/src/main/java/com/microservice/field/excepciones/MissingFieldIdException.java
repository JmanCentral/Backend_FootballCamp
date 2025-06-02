package com.microservice.field.excepciones;

public class MissingFieldIdException extends RuntimeException {
    public MissingFieldIdException(String message) {
        super(message);
    }
}
