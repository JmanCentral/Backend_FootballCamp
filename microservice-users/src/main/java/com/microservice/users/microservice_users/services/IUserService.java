package com.microservice.users.microservice_users.services;

import com.microservice.users.microservice_users.entities.User;
import com.microservice.users.microservice_users.http.request.UserDTO;
import com.microservice.users.microservice_users.http.response.CreateUserDTO;
import com.microservice.users.microservice_users.http.response.ReservationByUserResponse;

import java.util.List;

public interface IUserService {

    List<UserDTO> findAll();

    UserDTO findById(Long id);

    UserDTO save(CreateUserDTO dto);

    ReservationByUserResponse findReservationByIdUser(Long id);

}
