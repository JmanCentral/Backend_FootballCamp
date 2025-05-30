package com.microservice.reservations.controllers;

import com.microservice.reservations.entities.Reservation;
import com.microservice.reservations.http.request.ReservationDTO;
import com.microservice.reservations.http.response.CreateReservationDTO;
import com.microservice.reservations.services.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private IReservationService reservationService;

    @PostMapping("/registerReservation")
    public ResponseEntity<ReservationDTO> create(@RequestBody CreateReservationDTO dto) {
        ReservationDTO saved = reservationService.registerReservation(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/todo")
    public ResponseEntity<List<Reservation>> getAll() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getById(@PathVariable Long id) {
        return reservationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Manejar un DTO
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reservation>> getByUserId(@PathVariable Long userId) {
        List<Reservation> reservations = reservationService.findByUserId(userId);
        return ResponseEntity.ok(reservations);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reservationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
