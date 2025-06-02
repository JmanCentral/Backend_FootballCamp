package com.microservice.field.services;

import com.microservice.field.http.request.FieldRequestDTO;
import com.microservice.field.http.response.FieldResponseDTO;

import java.util.List;

public interface IFieldService {

    FieldResponseDTO save (FieldRequestDTO fieldRequest);

    FieldResponseDTO findById (String id);

    List<FieldResponseDTO> findAll ();

    FieldResponseDTO update (FieldRequestDTO fieldRequest);

    void delete (String id);

}
