package com.microservice.users.microservice_users.excepciones.user;

public class TelefonoYaRegistradoException extends RuntimeException {
    public TelefonoYaRegistradoException(String message) {
        super(message);
    }
}
