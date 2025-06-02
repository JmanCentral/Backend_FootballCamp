package com.microservice.field.entities;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "fields") // Nombre de la colección en MongoDB
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Field {

    @Id
    private String id_cancha; // Mongo usa String para ObjectId por defecto
    private TipoCancha nombre_cancha;
    private String descripcion_cancha;
    private Double precio_cancha;
    private String ubicacion_cancha;
}

