package com.microservice.field.controllers;

import com.microservice.field.excepciones.CannotDeleteFieldException;
import com.microservice.field.excepciones.FieldNotFoundException;
import com.microservice.field.excepciones.MissingFieldIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FieldNotFoundException.class)
    public ResponseEntity<String> handleFieldNotFoundException(FieldNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(MissingFieldIdException.class)
    public ResponseEntity<String> handleMissingFieldIdException(MissingFieldIdException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(CannotDeleteFieldException.class)
    public ResponseEntity<String> handleCannotDeleteFieldException(CannotDeleteFieldException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Manejo general de otras excepciones no controladas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno del servidor: " + ex.getMessage());
    }
}
