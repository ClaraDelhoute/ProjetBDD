package org.example.controller;

import org.example.classes.Commande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import services.CommandeService;

import java.util.List;

@Controller
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    // Afficher toutes les commandes
    @GetMapping("/dashboard")
    public String getAllCommandes(Model model) {
        commandeService.createCommandes();
        List<Commande> commandes = commandeService.getAllCommandes();
        System.out.println(commandes);
        model.addAttribute("commandes", commandes);
        return "dashboard";
    }
}