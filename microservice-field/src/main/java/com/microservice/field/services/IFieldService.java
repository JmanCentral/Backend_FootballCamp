package com.microservice.field.services;

import com.microservice.field.entities.Estado;
import com.microservice.field.http.request.FieldRequestDTO;
import com.microservice.field.http.response.FieldResponseDTO;

import java.util.List;

public interface IFieldService {

    FieldResponseDTO save (FieldRequestDTO fieldRequest);

    FieldResponseDTO findById (Long id);

    List<FieldResponseDTO> findAll ();

    FieldResponseDTO update (Long id , FieldRequestDTO fieldRequest);

    void delete (Long id);

    public void actualizarEstadoCancha(Long fieldId, Estado nuevoEstado);


}
