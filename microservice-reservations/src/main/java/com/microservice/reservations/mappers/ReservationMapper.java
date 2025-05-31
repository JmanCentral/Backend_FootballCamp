package com.microservice.reservations.mappers;

import com.microservice.reservations.entities.EstadoReserva;
import com.microservice.reservations.entities.Reservation;
import com.microservice.reservations.http.request.ReservationRequestDTO;
import com.microservice.reservations.http.response.ReservationResponseDTO;

public class ReservationMapper {

    public static ReservationResponseDTO toDTO(Reservation reservation) {
        return ReservationResponseDTO.builder()
                .id(reservation.getId())
                .nombreReserva(reservation.getNombreReserva())
                .fechaReserva(reservation.getFechaReserva())
                .horaInicio(reservation.getHoraInicio())
                .horaFin(reservation.getHoraFin())
                .estado(EstadoReserva.valueOf(reservation.getEstado().name())) // enum a String
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
                .estado(EstadoReserva.valueOf(dto.getEstado().name()))
                .userId(dto.getUserId())
                .fieldId(dto.getFieldId())
                .build();
    }

    public static void updateEntityFromDTO(ReservationRequestDTO dto, Reservation reservation) {
        reservation.setNombreReserva(dto.getNombreReserva());
        reservation.setFechaReserva(dto.getFechaReserva());
        reservation.setHoraInicio(dto.getHoraInicio());
        reservation.setHoraFin(dto.getHoraFin());

        if (dto.getEstado() != null) {
            reservation.setEstado(dto.getEstado());
        }

        reservation.setUserId(dto.getUserId());
        reservation.setFieldId(dto.getFieldId());
    }



}

