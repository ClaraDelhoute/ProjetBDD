package org.example.controller;

import org.example.classes.Commande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.example.services.CommandeService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @GetMapping("/commandes")
    public String getCommandesByFilter(
            @RequestParam(value = "dateDebut", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,

            @RequestParam(value = "dateFin", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin,

            @RequestParam(value = "membre", required = false) String membre,

            Model model) {

        // Convertir LocalDate en LocalDateTime pour le filtrage (début de journée / fin de journée)
        LocalDateTime startDate = (dateDebut != null) ? dateDebut.atStartOfDay() : null;
        LocalDateTime endDate = (dateFin != null) ? dateFin.atTime(LocalTime.MAX) : null;

        // Appel au service qui doit gérer le filtrage en fonction des critères fournis.
        List<Commande> commandes = commandeService.findByFilter(startDate, endDate, membre);

        model.addAttribute("commandes", commandes);
        return "dashboard";
    }

    // Afficher toutes les commandes
    @GetMapping("/dashboard")
    public String getAllCommandes(Model model) {
        List<Commande> commandes = commandeService.findAll();
        System.out.println(commandes);
        model.addAttribute("commandes", commandes);
        return "dashboard";
    }
}