package services;

import org.example.classes.Commande;
import org.example.classes.Materiel;
import org.example.classes.Membre;
import org.example.repository.CommandeRepository;
import org.example.repository.MembreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommandeServiceImpl implements CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private MembreRepository membreRepository;

    @Override
    public void createCommandes() {
        private List<Commande> commandes = new ArrayList<>();
        Membre vendeur = new Membre();
        Membre client = new Membre();

        vendeur.setNom("Vendeur");
        vendeur.setPrenom("Vendeur");
        vendeur.setEmail("vendeur@gmail.com");

        client.setNom("Client");
        client.setPrenom("Client");
        client.setEmail("client@gmail.com");

        membreRepository.save(vendeur);
        membreRepository.save(client);



        List<Materiel> materiels = new ArrayList<>();

        commandes.add(new Commande(1L, vendeur, client, LocalDateTime.now().minusDays(1), materiels, 100.0));
        commandes.add(new Commande(2L, vendeur, client, LocalDateTime.now().minusDays(2), materiels, 200.0));
        commandes.add(new Commande(3L, vendeur, client, LocalDateTime.now().minusDays(3), materiels, 150.0));

        commandeRepository.saveAll(commandes);
    }

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
    public Optional<Commande> getCommandeById(Long id) {
        return commandeRepository.findById(id);
    }

    @Override
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll(); // Récupère toutes les commandes
    }
}