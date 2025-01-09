package org.example.repository;

import org.example.classes.Groupe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupeRepository extends MongoRepository<Groupe, String> {
}
