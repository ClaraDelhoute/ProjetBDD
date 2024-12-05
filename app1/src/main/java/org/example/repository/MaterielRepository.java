package org.example.repository;

import org.example.classes.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterielRepository  extends JpaRepository<Materiel,String> {
}
