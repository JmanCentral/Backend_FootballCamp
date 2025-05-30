package com.microservice.reservations.mappers;

import com.microservice.reservations.entities.Reservation;
import com.microservice.reservations.http.request.ReservationRequestDTO;
import com.microservice.reservations.http.response.ReservationResponseDTO;

public class ReservationMapper {

    public static ReservationResponseDTO toDTO(Reservation reservation) {
        return ReservationRequestDTO.builder()
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

    public static Reservation toEntity(ReservationRequestDTO dto) {
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

