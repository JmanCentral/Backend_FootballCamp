package com.microservice.users.microservice_users.http.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserRequestDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String username;
    private String email;
    private String telefono;
    private String password;
}
