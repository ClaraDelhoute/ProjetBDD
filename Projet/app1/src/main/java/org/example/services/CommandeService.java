package org.example.services;

import org.example.classes.Commande;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

public interface CommandeService {
    void addCommande(Commande commande);
    void updateCommande(Commande commande);
    void deleteCommande(Commande commande);
    Optional<Commande> getCommandeById(String id);
    List<Commande> getAllCommandes();

    List<Commande> findAll();

    List<Commande> findByFilter(LocalDateTime startDate, LocalDateTime endDate, String membreName);
}
