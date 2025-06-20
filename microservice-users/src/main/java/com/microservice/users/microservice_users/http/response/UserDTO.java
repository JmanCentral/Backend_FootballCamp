package com.microservice.users.microservice_users.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String username;
    private String email;
    private String telefono;
}
