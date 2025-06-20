package com.microservice.reservations.excepciones;

public class ReservaNoEncontradaException extends RuntimeException {
    public ReservaNoEncontradaException(String message) {
        super(message);
    }
}
