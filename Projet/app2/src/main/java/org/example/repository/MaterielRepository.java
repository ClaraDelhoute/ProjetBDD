package org.example.repository;

import org.example.classes.Groupe;
import org.example.classes.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterielRepository  extends JpaRepository<Materiel,String> {
    public List<Materiel> findByGroupe(Groupe groupe);
    public List<Materiel> findByIdIn(List<Long> materielIds);
}
