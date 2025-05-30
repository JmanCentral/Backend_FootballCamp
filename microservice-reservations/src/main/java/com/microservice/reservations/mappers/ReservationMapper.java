package com.microservice.reservations.mappers;

import com.microservice.reservations.entities.EstadoReserva;
import com.microservice.reservations.entities.Reservation;
import com.microservice.reservations.http.request.ReservationDTO;
import com.microservice.reservations.http.response.CreateReservationDTO;

public class ReservationMapper {

    public static ReservationDTO toDTO(Reservation reservation) {
        return ReservationDTO.builder()
                .id(reservation.getId())
                .nombreReserva(reservation.getNombreReserva())
                .fechaReserva(reservation.getFechaReserva())
                .horaInicio(reservation.getHoraInicio())
                .horaFin(reservation.getHoraFin())
                .estado(reservation.getEstado()) // enum a String
                .userId(reservation.getUserId())
                .fieldId(reservation.getFieldId())
                .build();
    }

    public static Reservation toEntity(CreateReservationDTO dto) {
        return Reservation.builder()
                .id(dto.getId())
                .nombreReserva(dto.getNombreReserva())
                .fechaReserva(dto.getFechaReserva())
                .horaInicio(dto.getHoraInicio())
                .horaFin(dto.getHoraFin())
                .estado(dto.getEstado())
                .userId(dto.getUserId())
                .fieldId(dto.getFieldId())
                .build();
    }
}

