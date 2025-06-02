package com.microservice.field.persistencies;

import com.microservice.field.entities.Field;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepository extends MongoRepository<Field, Long> {


}
