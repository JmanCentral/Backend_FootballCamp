package com.microservice.users.microservice_users.controllers;

import com.microservice.users.microservice_users.excepciones.user.EmailYaRegistradoException;
import com.microservice.users.microservice_users.excepciones.user.TelefonoYaRegistradoException;
import com.microservice.users.microservice_users.excepciones.user.UserNotFoundException;
import com.microservice.users.microservice_users.excepciones.user.UsernameYaRegistradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailYaRegistradoException.class)
    public ResponseEntity<String> handleEmailDuplicado(EmailYaRegistradoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UsernameYaRegistradoException.class)
    public ResponseEntity<String> handleUsernameDuplicado(UsernameYaRegistradoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(TelefonoYaRegistradoException.class)
    public ResponseEntity<String> handleTelefonoDuplicado(TelefonoYaRegistradoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}

