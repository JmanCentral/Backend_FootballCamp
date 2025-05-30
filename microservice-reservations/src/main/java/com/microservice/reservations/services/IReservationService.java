package com.microservice.reservations.services;

import com.microservice.reservations.entities.Reservation;
import com.microservice.reservations.http.request.ReservationDTO;
import com.microservice.reservations.http.response.CreateReservationDTO;
import com.microservice.reservations.http.response.CreateUserDTO;

import java.util.List;
import java.util.Optional;

public interface IReservationService {

    CreateUserDTO findByIdUser(Long id);
    // Crear una nueva reserva
    ReservationDTO registerReservation(CreateReservationDTO reservationdto);

    // Obtener todas las reservas
    List<Reservation> findAll();

    // Obtener una reserva por su ID
    Optional<Reservation> findById(Long id);

    // Obtener reservas por ID de usuario (relación lógica, no directa)
    List<Reservation> findByUserId(Long userId);

    // Eliminar una reserva por su ID
    void deleteById(Long id);


}
