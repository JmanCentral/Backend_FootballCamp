package com.microservice.reservations.client;

import com.microservice.reservations.http.response.FieldResponseDTO;
import com.microservice.reservations.http.response.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-fields", url = "localhost:8100")
public interface FieldClient {
    @GetMapping("/api/fields/search/cancha/{id}")
    FieldResponseDTO get(@PathVariable("id") Long id);
}
