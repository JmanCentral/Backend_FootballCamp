package com.microservice.users.microservice_users.services;

import com.microservice.users.microservice_users.client.ReservationClient;
import com.microservice.users.microservice_users.excepciones.user.EmailYaRegistradoException;
import com.microservice.users.microservice_users.excepciones.user.TelefonoYaRegistradoException;
import com.microservice.users.microservice_users.excepciones.user.UsernameYaRegistradoException;
import com.microservice.users.microservice_users.http.request.ReservationDTO;
import com.microservice.users.microservice_users.entities.User;
import com.microservice.users.microservice_users.http.request.UserDTO;
import com.microservice.users.microservice_users.http.response.CreateUserDTO;
import com.microservice.users.microservice_users.http.response.ReservationByUserResponse;
import com.microservice.users.microservice_users.mappers.UserMapper;
import com.microservice.users.microservice_users.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReservationClient reservationClient;



    @Override
    public UserDTO save(CreateUserDTO dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new EmailYaRegistradoException("El correo ya está registrado.");
        }

        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new UsernameYaRegistradoException("El nombre de usuario ya existe.");
        }

        if (userRepository.existsByTelefono(dto.getTelefono())) {
            throw new TelefonoYaRegistradoException("El teléfono ya está registrado.");
        }

        User user = UserMapper.toEntity(dto);
        User savedUser = userRepository.save(user);

        return UserMapper.toDTO(savedUser);
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        return UserMapper.toDTO(user);
    }


    @Override
    public ReservationByUserResponse findReservationByIdUser(Long id) {

        // Consultar el usuario desde la base de datos del microservicio de usuarios
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        // Obtener las reservas del microservicio de reservas
        List<ReservationDTO> reservationDTO = reservationClient.getReservationsByUserId(id);

        // Construir y retornar la respuesta combinada
        return ReservationByUserResponse.builder()
                .nombre(user.getNombre())
                .apellido(user.getApellido())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .telefono(user.getTelefono())
                .reservation(reservationDTO)
                .build();
    }


}
