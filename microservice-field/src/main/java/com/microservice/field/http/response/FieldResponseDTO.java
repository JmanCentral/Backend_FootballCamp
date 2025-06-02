package com.microservice.field.http.response;

import com.microservice.field.entities.Estado;
import com.microservice.field.entities.TipoCancha;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FieldResponseDTO {

    private Long fieldId;
    private TipoCancha nombre_cancha;
    private String descripcion_cancha;
    private Double precio_cancha;
    private String ubicacion_cancha;
    private LocalDate fecha_cancha;
    @Enumerated(EnumType.STRING)
    private Estado estado_cancha;
}
