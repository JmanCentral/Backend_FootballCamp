package com.microservice.reservations.client;

import com.microservice.reservations.http.response.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "msvc-users", url = "localhost:8090")
public interface UserClient {

    @GetMapping("/api/users/search/{id}")
    UserResponseDTO obtenerPorId(@PathVariable("id") Long id);

}
