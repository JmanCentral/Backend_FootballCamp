package com.microservice.field.controllers;

import com.microservice.field.http.request.FieldRequestDTO;
import com.microservice.field.http.response.FieldResponseDTO;
import com.microservice.field.services.IFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fields")
public class FieldController {

    @Autowired
    private IFieldService fieldService;

    @PostMapping("/registerField")
    public ResponseEntity<FieldResponseDTO> create(@RequestBody FieldRequestDTO dto) {
        FieldResponseDTO saved = fieldService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/All")
    public ResponseEntity<List<FieldResponseDTO>> getAll() {
        List<FieldResponseDTO> search = fieldService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(search);
    }

    @GetMapping("/search/cancha/{id}")
    public ResponseEntity<FieldResponseDTO> get(@PathVariable Long id) {
        FieldResponseDTO dto = fieldService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PutMapping("/update/cancha/{id}")
    public ResponseEntity<FieldResponseDTO> update(@PathVariable Long id, @RequestBody FieldRequestDTO dto) {
        FieldResponseDTO saved = fieldService.update(id , dto);
        return ResponseEntity.status(HttpStatus.OK).body(saved);
    }

    @DeleteMapping("/delete/cancha/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        fieldService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

