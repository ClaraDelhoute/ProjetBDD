package services;

import org.example.classes.Commande;

import java.util.List;
import java.util.Optional;

public interface CommandeService {
    void addCommande(Commande commande);
    void updateCommande(Commande commande);
    void deleteCommande(Commande commande);
    Optional<Commande> getCommandeById(String id);
    List<Commande> getAllCommandes();
}
