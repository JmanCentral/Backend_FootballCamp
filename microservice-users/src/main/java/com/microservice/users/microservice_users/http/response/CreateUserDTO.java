package com.microservice.users.microservice_users.http.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {
    private String nombre;
    private String apellido;
    private String username;
    private String email;
    private String telefono;
    private String password;
}

