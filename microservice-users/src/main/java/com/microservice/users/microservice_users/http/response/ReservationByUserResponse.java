package com.microservice.users.microservice_users.http.response;

import com.microservice.users.microservice_users.http.request.ReservationDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationByUserResponse {

    private String nombre;
    private String apellido;
    private String username;
    private String password;
    private String email;
    private String telefono;
    private List<ReservationDTO> reservation;

}
