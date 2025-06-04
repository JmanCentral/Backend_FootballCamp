package com.microservice.reservations.services;

import com.microservice.reservations.client.FieldClient;
import com.microservice.reservations.client.UserClient;
import com.microservice.reservations.entities.Reservation;
import com.microservice.reservations.excepciones.*;
import com.microservice.reservations.http.request.ReservationRequestDTO;
import com.microservice.reservations.http.response.ReservationResponseDTO;
import com.microservice.reservations.kafka.ReservaEventDTO;
import com.microservice.reservations.mappers.ReservationMapper;
import com.microservice.reservations.persistencies.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements IReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private FieldClient fieldClient;

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;


    @Override
    public ReservationResponseDTO registerReservation(ReservationRequestDTO reservationDTO) {


        try {
            userClient.obtenerPorId(reservationDTO.getUserId());
        } catch (Exception e) {
            throw new UsuarioNoEncontradoException("El usuario con ID " + reservationDTO.getUserId() + " no existe.");
        }

        try {
            fieldClient.get(reservationDTO.getFieldId());
        } catch (Exception e) {
            throw new CanchaNoEncontradaException("La cancha  con ID " + reservationDTO.getFieldId() + " no existe.");
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


        enviarEventoCanchaReservada(
                saved.getFieldId(),
                "OCUPADA"
        );

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
    public ReservationResponseDTO update(Long id, ReservationRequestDTO reservationDTO) {

        // 1. Verificar existencia de reserva
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservaNoEncontradaException("La reserva con ID " + id + " no existe."));

        try {
            userClient.obtenerPorId(reservationDTO.getUserId());
        } catch (Exception e) {
            throw new UsuarioNoEncontradoException("El usuario con ID " + reservationDTO.getUserId() + " no existe.");
        }

        if (reservationRepository.existsByNombreReserva(reservationDTO.getNombreReserva())) {
            throw new NombreEnUsoException("El nombre de la reserva ya está en uso.");
        }

        // 4. Validar conflictos de horario
        List<Reservation> conflictos = reservationRepository.findConflictingReservations(
                reservationDTO.getFechaReserva(), reservationDTO.getHoraInicio(), reservationDTO.getHoraFin());

        if (!conflictos.isEmpty()) {
            throw new ConflictoReservaException("Ya existe una reserva en ese horario.");
        }

        ReservationMapper.updateEntityFromDTO(reservationDTO, existingReservation);

        Reservation updated = reservationRepository.save(existingReservation);
        return ReservationMapper.toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }

    public void enviarEventoCanchaReservada(Long fieldId, String nuevoEstado) {
        ReservaEventDTO eventDTO = new ReservaEventDTO();
        eventDTO.setFieldId(fieldId);
        eventDTO.setStatus(nuevoEstado);
        kafkaTemplate.send("reservas-topic", eventDTO); // Envía el Map como JSON
    }

}
