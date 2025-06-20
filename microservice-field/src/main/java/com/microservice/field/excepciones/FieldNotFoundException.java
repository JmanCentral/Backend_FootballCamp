package com.microservice.field.excepciones;

import org.aspectj.bridge.IMessage;

public class FieldNotFoundException extends RuntimeException {
    public FieldNotFoundException(String message) {
        super(message);
    }
}

