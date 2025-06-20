package com.microservice.reservations.kafka;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservaEventDTO {
    private Long fieldId;
    private String status;

}
