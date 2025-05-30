package com.microservice.users.microservice_users.excepciones.user;

public class EmailYaRegistradoException extends RuntimeException {
    public EmailYaRegistradoException(String message) {
        super(message);
    }
}
