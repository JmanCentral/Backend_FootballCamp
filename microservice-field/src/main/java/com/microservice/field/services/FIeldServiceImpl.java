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
    public FieldResponseDTO findById(Long id) {
        Field field = fieldRepository.findByFieldId(id)
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
    public FieldResponseDTO update(Long id, FieldRequestDTO fieldRequest) {
        if (id == null) {
            throw new IllegalArgumentException("ID requerido para actualizar la cancha.");
        }

        Field existing = fieldRepository.findByFieldId(id)
                .orElseThrow(() -> new MissingFieldIdException("Cancha no encontrada con ID: " + id));

        // Aplicamos los cambios del DTO a la entidad existente
        FieldMapper.updateEntity(existing, fieldRequest);

        Field updated = fieldRepository.save(existing);
        return FieldMapper.toDTO(updated);
    }


    @Override
    public void delete(Long id) {
        if (!fieldRepository.existsByFieldId(id)) {
            throw new CannotDeleteFieldException("No se puede eliminar. Cancha no encontrada con ID: " + id);
        }
        fieldRepository.deleteByFieldId(id);
    }
}
