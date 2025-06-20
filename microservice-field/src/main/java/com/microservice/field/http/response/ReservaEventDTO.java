package com.microservice.field.http.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservaEventDTO {
    private Long fieldId;
    private String status;
}