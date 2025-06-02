package com.microservice.field.entities;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "fields") // Nombre de la colección en MongoDB
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Field {

    @Id
    private String id;
    @Indexed(unique = true)
    private Long fieldId;
    private TipoCancha nombre_cancha;
    private String descripcion_cancha;
    private Double precio_cancha;
    private String ubicacion_cancha;
    private LocalDate fecha_cancha;
    @Enumerated(EnumType.STRING)
    private Estado estado_cancha;

}