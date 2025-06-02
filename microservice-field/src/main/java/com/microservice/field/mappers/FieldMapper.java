package com.microservice.field.mappers;

import com.microservice.field.entities.Field;
import com.microservice.field.http.request.FieldRequestDTO;
import com.microservice.field.http.response.FieldResponseDTO;

public class FieldMapper {

    // De entidad a DTO de respuesta (Field → FieldResponse)
    public static FieldResponseDTO toDTO(Field field) {
        return FieldResponseDTO.builder()
                .id_cancha(field.getId_cancha())
                .nombre_cancha(field.getNombre_cancha())
                .descripcion_cancha(field.getDescripcion_cancha())
                .precio_cancha(field.getPrecio_cancha())
                .ubicacion_cancha(field.getUbicacion_cancha())
                .build();
    }

    // De DTO de entrada a entidad (FieldRequest → Field)
    public static Field toEntity(FieldRequestDTO dto) {
        return Field.builder()
                .id_cancha(dto.getId_cancha())
                .nombre_cancha(dto.getNombre_cancha())
                .descripcion_cancha(dto.getDescripcion_cancha())
                .precio_cancha(dto.getPrecio_cancha())
                .ubicacion_cancha(dto.getUbicacion_cancha())
                .build();
    }

    // Actualiza una entidad existente con datos del DTO (solo para updates)
    public static void updateEntity(Field field, FieldRequestDTO dto) {
        field.setNombre_cancha(dto.getNombre_cancha());
        field.setDescripcion_cancha(dto.getDescripcion_cancha());
        field.setPrecio_cancha(dto.getPrecio_cancha());
        field.setUbicacion_cancha(dto.getUbicacion_cancha());
    }
}



