package com.microservice.reservations.client;

import com.microservice.reservations.http.request.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-users", url = "localhost:8090")
public interface UserClient {

    @GetMapping("/search/{id}")
    ResponseEntity<UserDTO> obtenerPorId(@PathVariable Long id);

}
