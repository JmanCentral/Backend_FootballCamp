package com.microservice.reservations.services;

import com.microservice.reservations.entities.Reservation;
import com.microservice.reservations.http.request.ReservationRequestDTO;
import com.microservice.reservations.http.response.ReservationResponseDTO;
import com.microservice.reservations.http.response.UserResponseDTO;

import java.util.List;
import java.util.Optional;

public interface IReservationService {

    // Crear una nueva reserva
    ReservationResponseDTO registerReservation(ReservationRequestDTO reservationdto);

    // Obtener todas las reservas
    List<ReservationResponseDTO> findAll();

    // Obtener una reserva por su ID
    ReservationResponseDTO findById(Long id);

    // Obtener reservas por ID de usuario (relación lógica, no directa)
    List<ReservationResponseDTO> findByUserId(Long userId);

    // Eliminar una reserva por su ID
    void deleteById(Long id);


}
