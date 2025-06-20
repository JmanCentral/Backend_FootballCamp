package com.microservice.field.excepciones;

public class CannotDeleteFieldException extends RuntimeException {
    public CannotDeleteFieldException(String message) {
        super(message);
    }
}
