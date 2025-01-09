package services;


import org.example.classes.Groupe;
import org.example.classes.Membre;

import java.util.List;
import java.util.Optional;

public interface GroupeService {
    void addGroupe(Groupe groupe);
    void updateGroupe(Groupe groupe);
    void deleteGroupe(Groupe groupe);
    Optional<Groupe> getGroupeById(String id);
    List<Groupe> getAllGroupes();

    Groupe getGroupeByMembre(Membre membre);
}