package com.microservice.users.microservice_users.mappers;

import com.microservice.users.microservice_users.entities.User;
import com.microservice.users.microservice_users.http.request.UserDTO;
import com.microservice.users.microservice_users.http.response.CreateUserDTO;

public class UserMapper {

    public static User toEntity(CreateUserDTO dto) {
        return User.builder()
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .email(dto.getEmail())
                .username(dto.getUsername())
                .telefono(dto.getTelefono())
                .password(dto.getPassword())
                .build();
    }

    public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .nombre(user.getNombre())
                .apellido(user.getApellido())
                .email(user.getEmail())
                .username(user.getUsername())
                .telefono(user.getTelefono())
                .build();
    }
}

