package org.example.repository;

import org.example.classes.Materiel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterielRepository  extends MongoRepository<Materiel,String> {
}
