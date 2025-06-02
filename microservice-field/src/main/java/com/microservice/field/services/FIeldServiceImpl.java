package com.microservice.field.services;


import com.microservice.field.entities.Field;
import com.microservice.field.excepciones.CannotDeleteFieldException;
import com.microservice.field.excepciones.MissingFieldIdException;
import com.microservice.field.http.request.FieldRequestDTO;
import com.microservice.field.http.response.FieldResponseDTO;
import com.microservice.field.mappers.FieldMapper;
import com.microservice.field.persistencies.FieldRepository;
import org.springframework.beans.factory.FactoryBeanNotInitializedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FIeldServiceImpl implements IFieldService {

    @Autowired
    private FieldRepository fieldRepository;

    @Override
    public FieldResponseDTO save(FieldRequestDTO fieldRequest) {
        Field field = FieldMapper.toEntity(fieldRequest);
        Field saved = fieldRepository.save(field);
        return FieldMapper.toDTO(saved);
    }

    @Override
    public FieldResponseDTO findById(String id) {
        Field field = fieldRepository.findById(id)
                .orElseThrow(() -> new FactoryBeanNotInitializedException("Cancha no encontrada con ID: " + id));
        return FieldMapper.toDTO(field);
    }

    @Override
    public List<FieldResponseDTO> findAll() {
        return fieldRepository.findAll()
                .stream()
                .map(FieldMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FieldResponseDTO update(FieldRequestDTO fieldRequest) {
        if (fieldRequest.getId_cancha() == null) {
            throw new IllegalArgumentException("ID requerido para actualizar la cancha.");
        }

        Field existing = fieldRepository.findById(fieldRequest.getId_cancha())
                .orElseThrow(() -> new MissingFieldIdException("Cancha no encontrada con ID: " + fieldRequest.getId_cancha()));

        // Aplicamos cambios
        FieldMapper.updateEntity(existing, fieldRequest);

        Field updated = fieldRepository.save(existing);
        return FieldMapper.toDTO(updated);
    }

    @Override
    public void delete(String id) {
        if (!fieldRepository.existsById(id)) {
            throw new CannotDeleteFieldException("No se puede eliminar. Cancha no encontrada con ID: " + id);
        }
        fieldRepository.deleteById(id);
    }
}
