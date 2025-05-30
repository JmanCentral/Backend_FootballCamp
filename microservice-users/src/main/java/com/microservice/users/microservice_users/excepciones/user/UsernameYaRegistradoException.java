package com.microservice.users.microservice_users.excepciones.user;

public class UsernameYaRegistradoException extends RuntimeException {
    public UsernameYaRegistradoException(String message) {
        super(message);
    }
}
