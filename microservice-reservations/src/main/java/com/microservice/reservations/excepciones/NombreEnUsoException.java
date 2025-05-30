package com.microservice.reservations.excepciones;

public class NombreEnUsoException extends RuntimeException {
    public NombreEnUsoException(String message) {
        super(message);
    }
}
