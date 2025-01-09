package org.example.repository;

import org.example.classes.Groupe;
import org.example.classes.Materiel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterielRepository  extends MongoRepository<Materiel,String> {
    public List<Materiel> findByGroupe(Groupe groupe);
    public List<Materiel> findByIdMaterielIn(List<Long> materielIds);
}
