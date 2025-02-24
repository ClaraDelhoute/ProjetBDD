package org.example.repository;

import org.example.classes.Membre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembreRepository extends MongoRepository<Membre,Long> {
    Membre getMembreByIdMembre(Long id);
    Membre getMembreByNom(String nom);

    Membre getMembreByNomAndPassword(String nom, String password);
}
