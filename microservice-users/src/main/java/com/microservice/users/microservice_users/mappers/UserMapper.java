package com.microservice.users.microservice_users.mappers;

import com.microservice.users.microservice_users.entities.User;
import com.microservice.users.microservice_users.http.response.UserDTO;
import com.microservice.users.microservice_users.http.request.UserRequestDTO;

public class UserMapper {


    // De entidad a DTO de respuesta (sin password)
    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setNombre(user.getNombre());
        dto.setApellido(user.getApellido());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setTelefono(user.getTelefono());
        return dto;
    }

    // De DTO de entrada a entidad
    public static User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setId(dto.getId()); // Solo si lo usas en update
        user.setNombre(dto.getNombre());
        user.setApellido(dto.getApellido());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setTelefono(dto.getTelefono());
        return user;
    }

    // Actualiza una entidad existente con datos del DTO
    public static void updateEntity(User user, UserRequestDTO dto) {
        user.setNombre(dto.getNombre());
        user.setApellido(dto.getApellido());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setTelefono(dto.getTelefono());
    }
}

