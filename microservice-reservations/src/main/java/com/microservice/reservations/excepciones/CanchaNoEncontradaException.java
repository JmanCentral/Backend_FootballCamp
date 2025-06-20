package com.microservice.reservations.excepciones;

public class CanchaNoEncontradaException extends RuntimeException {
    public CanchaNoEncontradaException(String message) {
        super(message);
    }
}
