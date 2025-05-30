package com.microservice.reservations.services;

import com.microservice.reservations.client.UserClient;
import com.microservice.reservations.entities.Reservation;
import com.microservice.reservations.excepciones.ConflictoReservaException;
import com.microservice.reservations.excepciones.NombreEnUsoException;
import com.microservice.reservations.excepciones.UsuarioNoEncontradoException;
import com.microservice.reservations.http.request.ReservationRequestDTO;
import com.microservice.reservations.http.response.ReservationResponseDTO;
import com.microservice.reservations.http.response.UserResponseDTO;
import com.microservice.reservations.mappers.ReservationMapper;
import com.microservice.reservations.persistencies.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements IReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserClient userClient;

    @Override
    public ReservationResponseDTO registerReservation(ReservationRequestDTO reservationDTO) {

        try {
            UserResponseDTO user = userClient.obtenerPorId(reservationDTO.getUserId());
        } catch (Exception e) {
            throw new UsuarioNoEncontradoException("El usuario con ID " + reservationDTO.getUserId() + " no existe.");
        }

        // Validar nombre de reserva único
        if (reservationRepository.existsByNombreReserva(reservationDTO.getNombreReserva())) {
            throw new NombreEnUsoException("El nombre de la reserva ya está en uso.");
        }


        LocalDate fecha = reservationDTO.getFechaReserva();
        LocalTime horaInicio = reservationDTO.getHoraInicio();
        LocalTime horaFin = reservationDTO.getHoraFin();

        List<Reservation> conflictos = reservationRepository.findConflictingReservations(fecha, horaInicio, horaFin);

        if (!conflictos.isEmpty()) {
            throw new ConflictoReservaException("Ya existe una reserva en ese horario.");
        }

        Reservation reservation = ReservationMapper.toEntity(reservationDTO);
        reservation.setFechaReserva(fecha); // no se pisa con now(), se toma del DTO

        Reservation saved = reservationRepository.save(reservation);
        return ReservationMapper.toDTO(saved);
    }


    @Override
    public List<ReservationResponseDTO> findAll() {
        List<Reservation> users = reservationRepository.findAll();
        return users.stream()
                .map(ReservationMapper::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    public ReservationResponseDTO findById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        return ReservationMapper.toDTO(reservation);
    }

    @Override
    public List<ReservationResponseDTO> findByUserId(Long userId) {

        List<Reservation> reservations = reservationRepository.findByUserId(userId);
        return reservations.stream().map(ReservationMapper::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }



}
