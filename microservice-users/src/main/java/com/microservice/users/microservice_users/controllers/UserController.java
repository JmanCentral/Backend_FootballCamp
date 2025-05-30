package com.microservice.users.microservice_users.controllers;

import com.microservice.users.microservice_users.http.response.UserDTO;
import com.microservice.users.microservice_users.http.request.UserRequestDTO;
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
    public ResponseEntity<UserDTO> register(@RequestBody UserRequestDTO dto) {
        UserDTO saved = userService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/All")
    public ResponseEntity<List<UserDTO>> obtenerTodos() {
        return ResponseEntity.ok(userService.findAll());
    }

    // ✅ Actualizar un usuario
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO dto) {
        dto.setId(id); // Asignar el id desde el path al DTO
        UserDTO updated = userService.update(dto);
        return ResponseEntity.ok(updated);
    }

    // ✅ Obtener un usuario por ID
    @GetMapping("/search/{id}")
    public ResponseEntity<UserDTO> obtenerPorId(@PathVariable Long id) {
        UserDTO user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    // ✅ Eliminar un usuario
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
