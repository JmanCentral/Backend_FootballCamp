package com.microservice.reservations.services;

import com.microservice.reservations.entities.Reservation;
import com.microservice.reservations.excepciones.ConflictoReservaException;
import com.microservice.reservations.http.request.ReservationDTO;
import com.microservice.reservations.http.response.CreateReservationDTO;
import com.microservice.reservations.mappers.ReservationMapper;
import com.microservice.reservations.persistencies.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements IReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public ReservationDTO registerReservation(CreateReservationDTO reservationDTO) {
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
    public List<Reservation> findAll() {
        return (List<Reservation>) reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public List<Reservation> findByUserId(Long userId) {
        List<Reservation> reservations = reservationRepository.findByUserId(userId);

        // Imprimir en consola
        reservations.forEach(System.out::println); // forma elegante

        return reservations;
    }


    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }



}
