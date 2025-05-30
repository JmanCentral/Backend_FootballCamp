package com.microservice.users.microservice_users.services;

import com.microservice.users.microservice_users.http.response.UserDTO;
import com.microservice.users.microservice_users.http.request.UserRequestDTO;

import java.util.List;

public interface IUserService {

    UserDTO save(UserRequestDTO dto);
    // Entrada con datos del cliente
    UserDTO findById(Long id);
    // Devuelve datos sin password
    List<UserDTO> findAll();
    // Lista de respuestas seguras
    UserDTO update(UserRequestDTO dto);

    public void deleteById(Long id);


}
