package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.example.classes.Commande;
import org.example.classes.Groupe;
import org.example.classes.Materiel;
import org.example.classes.Membre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.CommandeService;
import services.GroupeService;
import services.MaterielService;
import services.MembreService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CommandeController {

    @Autowired
    CommandeService commandeService;
    @Autowired
    MembreService  membreService;
    @Autowired
    GroupeService groupeService;
    @Autowired
    MaterielService materielService;

    @GetMapping("/commande")
    public String commande(Model model, HttpSession session){
        Membre client = (Membre)session.getAttribute("user");
        if(client !=null){
            model.addAttribute("client",client);
        }
        List<Membre> vendeurs = membreService.getMembresVendeur();
        if(vendeurs !=null){
            List<Groupe> groupes = new ArrayList<>();
            List<Materiel> materiels = new ArrayList<>();
            for(Membre vendeur : vendeurs){
                Groupe groupe = groupeService.getGroupeByMembre(vendeur);
                if(!groupes.contains(groupe)){
                    groupes.add(groupe);
                    materiels.addAll(materielService.getAllMaterielsByGroupe(groupe));
                }
            }
            model.addAttribute("vendeurs",vendeurs);
            model.addAttribute("materiels",materiels);
        }
        return "commande";
    }

    @PostMapping("/shop")
    public String shop(@RequestParam Long clientId,
                       @RequestParam Long vendeurId,
                       @RequestParam List<Long> materielIds,
                       @RequestParam Double prixTotal) {

        // Récupérer le client et le vendeur depuis la base de données
        Membre client = membreService.getMembreById(clientId);
        Membre vendeur = membreService.getMembreById(vendeurId);

        // Récupérer la liste des matériels sélectionnés par leurs IDs
        List<Materiel> materiels = materielService.getMaterielsByIds(materielIds);

        // Créer une nouvelle commande
        Commande commande = new Commande();
        commande.setCommandesAchats(client); // Associe le client à la commande
        commande.setCommandesVentes(vendeur); // Associe le vendeur à la commande
        commande.setListe(materiels); // Associe la liste de matériels à la commande
        commande.setPrixTotal(prixTotal); // Définit le prix total
        commande.setDateCommande(LocalDateTime.now()); // Définit la date de la commande

        // Ajouter la commande à la base de données
        commandeService.addCommande(commande);

        // Rediriger vers la page d'accueil ou une page de succès
        return "index";
    }
}
