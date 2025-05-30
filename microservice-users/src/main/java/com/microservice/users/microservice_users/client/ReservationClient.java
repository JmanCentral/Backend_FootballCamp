package com.microservice.users.microservice_users.client;

import com.microservice.users.microservice_users.http.request.ReservationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-reservation", url = "localhost:8862")
public interface ReservationClient {

    @GetMapping("/api/reservations/user/{userId}")
    List<ReservationDTO> getReservationsByUserId(@PathVariable("userId") Long userId);

}

