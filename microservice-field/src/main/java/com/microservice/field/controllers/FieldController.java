package com.microservice.field.controllers;

import com.microservice.field.http.request.FieldRequestDTO;
import com.microservice.field.http.response.FieldResponseDTO;
import com.microservice.field.services.IFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
