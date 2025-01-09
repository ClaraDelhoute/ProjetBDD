package org.example.repository;

import org.example.classes.Groupe;
import org.example.classes.Membre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupeRepository extends JpaRepository<Groupe, String> {
    public Groupe findGroupeByMembres(Membre membre);
}
