package org.example.repository;

import org.example.classes.Membre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembreRepository extends MongoRepository<Membre,String> {
    Membre getMembreByIdMembre(String id);
    Membre getMembreByNom(String nom);

    Membre getMembreByNomAndPassword(String nom, String password);
}
