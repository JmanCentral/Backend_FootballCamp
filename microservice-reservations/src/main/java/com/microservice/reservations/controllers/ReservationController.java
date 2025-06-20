package com.microservice.reservations.controllers;

import com.microservice.reservations.entities.Reservation;
import com.microservice.reservations.http.request.ReservationRequestDTO;
import com.microservice.reservations.http.response.ReservationResponseDTO;
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
    public ResponseEntity<ReservationResponseDTO> create(@RequestBody ReservationRequestDTO dto) {
        ReservationResponseDTO saved = reservationService.registerReservation(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReservationResponseDTO>> getAll() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> getById(@PathVariable Long id) {
        try {
            ReservationResponseDTO dto = reservationService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationResponseDTO>> getByUserId(@PathVariable Long userId) {
        List<ReservationResponseDTO> reservations = reservationService.findByUserId(userId);
        return ResponseEntity.ok(reservations);
    }

    @PutMapping("update/reservation/{id}")
    public ResponseEntity<ReservationResponseDTO> update(@PathVariable Long id , @RequestBody ReservationRequestDTO dto) {
        ReservationResponseDTO updated = reservationService.update(id , dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reservationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

