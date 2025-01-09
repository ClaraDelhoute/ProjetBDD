package org.example.repository;

import org.example.classes.Groupe;
import org.example.classes.Membre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupeRepository extends MongoRepository<Groupe, String> {
    public Groupe findGroupeByMembres(Membre membre);
}
