package org.example.services;

import org.example.classes.Membre;

import java.util.List;

public interface MembreService {
    List<Membre> getAllMembre();
    Membre getMembreByIdMembre(String id);
    void saveMembre(Membre membre);
    void updateMembre(Membre membre);
    void deleteMembre(Membre membre);
    Membre getMembreByNom(String nom);
    Membre getMembreByNomAndPassword(String nom, String password);

    List<Membre> getMembresClients();
    List<Membre> getMembresVendeur();
}
