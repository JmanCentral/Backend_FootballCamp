package com.microservice.field.http.request;

import com.microservice.field.entities.TipoCancha;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FieldRequestDTO {

    private String id_cancha;
    private TipoCancha nombre_cancha;
    private String descripcion_cancha;
    private Double precio_cancha;
    private String ubicacion_cancha;
}
