package com.microservice.reservations.controllers;

import com.microservice.reservations.excepciones.ConflictoReservaException;
import com.microservice.reservations.excepciones.NombreEnUsoException;
import com.microservice.reservations.excepciones.ReservaNoEncontradaException;
import com.microservice.reservations.excepciones.UsuarioNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConflictoReservaException.class)
    public ResponseEntity<String> handleHorarioReservado(ConflictoReservaException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NombreEnUsoException.class)
    public ResponseEntity<String> handleNombreReservado(NombreEnUsoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<String> handleUsuarioNoEncontrado(UsuarioNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ReservaNoEncontradaException.class)
    public ResponseEntity<String> handleReservaNoEncontrada(ReservaNoEncontradaException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }



}
