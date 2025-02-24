package org.example.repository;

import org.example.classes.Membre;
import org.example.classes.TypeMembre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembreRepository extends MongoRepository<Membre,String> {
    Membre getMembreByIdMembre(Long id);
    Membre getMembreByNom(String nom);
    Membre getMembreByNomAndPassword(String nom, String password);
    List<Membre> getMembreByTypeMembre(TypeMembre typeClient);
}
