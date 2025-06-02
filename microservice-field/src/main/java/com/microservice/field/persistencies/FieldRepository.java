package com.microservice.field.persistencies;

import com.microservice.field.entities.Field;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FieldRepository extends MongoRepository<Field, String> {


    Optional<Field> findByFieldId(Long idCancha);

    boolean existsByFieldId(Long idCancha);

    void deleteByFieldId(Long idCancha);


}
