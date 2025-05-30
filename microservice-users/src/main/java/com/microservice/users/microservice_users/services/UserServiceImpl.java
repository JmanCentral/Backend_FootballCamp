package com.microservice.users.microservice_users.services;

import com.microservice.users.microservice_users.excepciones.user.EmailYaRegistradoException;
import com.microservice.users.microservice_users.excepciones.user.TelefonoYaRegistradoException;
import com.microservice.users.microservice_users.excepciones.user.UserNotFoundException;
import com.microservice.users.microservice_users.excepciones.user.UsernameYaRegistradoException;
import com.microservice.users.microservice_users.entities.User;
import com.microservice.users.microservice_users.http.response.UserDTO;
import com.microservice.users.microservice_users.http.request.UserRequestDTO;
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


    @Override
    public UserDTO save(UserRequestDTO dto) {

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
    public UserDTO update(UserRequestDTO dto) {
        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con ID: " + dto.getId()));

        // Validar duplicados (excepto si ya son del mismo usuario)
        if (userRepository.existsByEmail(dto.getEmail()) && !dto.getEmail().equals(user.getEmail())) {
            throw new EmailYaRegistradoException("El correo ya está registrado.");
        }

        if (userRepository.existsByUsername(dto.getUsername()) && !dto.getUsername().equals(user.getUsername())) {
            throw new UsernameYaRegistradoException("El nombre de usuario ya existe.");
        }

        if (userRepository.existsByTelefono(dto.getTelefono()) && !dto.getTelefono().equals(user.getTelefono())) {
            throw new TelefonoYaRegistradoException("El teléfono ya está registrado.");
        }

        UserMapper.updateEntity(user, dto);
        userRepository.save(user);

        return UserMapper.toDTO(user);
    }



    @Override
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        return UserMapper.toDTO(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
