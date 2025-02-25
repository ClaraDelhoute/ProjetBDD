package org.example.services;

import org.example.classes.Commande;
import org.example.repository.CommandeRepository;
import org.example.repository.MembreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.example.classes.Commande;
import org.example.classes.Membre;
import org.example.services.CommandeService;
import org.example.services.MembreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class CommandeServiceImpl implements CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private MembreRepository membreRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MembreService membreService;

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


    @Override
    public List<Commande> findByFilter(LocalDateTime startDate, LocalDateTime endDate, String membreName) {
        Query query = new Query();

        // Combiner les contraintes de date dans un seul Criteria
        if (startDate != null || endDate != null) {
            Criteria dateCriteria = Criteria.where("date");
            if (startDate != null) {
                dateCriteria = dateCriteria.gte(startDate);
            }
            if (endDate != null) {
                dateCriteria = dateCriteria.lte(endDate);
            }
            query.addCriteria(dateCriteria);
        }

        // Filtrage par nom de membre
        if (membreName != null && !membreName.trim().isEmpty()) {
            Membre m = membreService.getMembreByNom(membreName);
            if (m != null) {
                query.addCriteria(Criteria.where("idMembre.$id").is(m.getIdMembre()));
            } else {
                // Aucun membre correspondant, retourner une liste vide.
                return new ArrayList<>();
            }
        }

        return mongoTemplate.find(query, Commande.class);
    }
}