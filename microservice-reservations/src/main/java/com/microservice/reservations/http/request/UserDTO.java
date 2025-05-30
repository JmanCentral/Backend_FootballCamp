package com.microservice.reservations.http.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String username;
    private String email;
    private String telefono;
}
