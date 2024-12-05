package org.example.repository;

import org.example.classes.Membre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembreRepository extends JpaRepository<Membre,Long> {
    Membre getMemberById(Long id);
    Membre getMembreByNom(String nom);
    Membre getMembreByNomAndPassword(String nom, String password);
}
