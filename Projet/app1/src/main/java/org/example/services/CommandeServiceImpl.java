package org.example.services;

import org.example.classes.Commande;
import org.example.repository.CommandeRepository;
import org.example.repository.MembreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeServiceImpl implements CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private MembreRepository membreRepository;


    @Override
    public void addCommande(Commande commande) {
        commandeRepository.save(commande);
    }

    @Override
    public void updateCommande(Commande commande) {
        commandeRepository.save(commande);
    }

    @Override
    public void deleteCommande(Commande commande) {
        commandeRepository.delete(commande);
    }

    @Override
    public Optional<Commande> getCommandeById(String id) {
        return commandeRepository.findById(id);
    }

    @Override
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll(); // Récupère toutes les commandes
    }

    @Override
    public List<Commande> findAll() {
        return commandeRepository.findAll();
    }
}