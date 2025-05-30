package com.microservice.users.microservice_users.http.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String estado;
    private Long userId;
}

