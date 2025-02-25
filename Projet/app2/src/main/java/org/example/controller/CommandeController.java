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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.example.services.CommandeService;
import org.example.services.GroupeService;
import org.example.services.MaterielService;
import org.example.services.MembreService;

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
    public String shop(@RequestParam("clientId") String clientId,
                       @RequestParam("vendeurId") String vendeurId,
                       @RequestParam("materielIds") List<String> materielIds,
                       @RequestParam("prixTotal") Double prixTotal) {

        Membre client = membreService.getMembreByIdMembre(clientId);
        Membre vendeur = membreService.getMembreByIdMembre(vendeurId);

        List<Materiel> materiels = materielService.getMaterielsByIds(materielIds);

        Commande commande = new Commande();
        commande.setIdMembre(client);
        commande.setIdVendeur(vendeur);
        commande.setListe(materiels);
        commande.setPrixTotal(prixTotal);
        commande.setDate(LocalDateTime.now());

        commandeService.addCommande(commande);

        return "index";
    }
}
