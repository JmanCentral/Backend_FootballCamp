package com.microservice.users.microservice_users.controllers;

import com.microservice.users.microservice_users.entities.User;
import com.microservice.users.microservice_users.http.request.UserDTO;
import com.microservice.users.microservice_users.http.response.CreateUserDTO;
import com.microservice.users.microservice_users.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody CreateUserDTO dto) {
        UserDTO saved = userService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/All")
    public ResponseEntity<List<UserDTO>> obtenerTodos() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<UserDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/Usuarios/reservas/{idUser}")
    public ResponseEntity<?> findUserByIdUser(@PathVariable Long idUser) {
        return ResponseEntity.ok(userService.findReservationByIdUser(idUser));
    }


}
