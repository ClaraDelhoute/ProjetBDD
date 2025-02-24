package org.example.controller;

import org.example.classes.Groupe;
import org.example.classes.Membre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.example.services.GroupeService;
import org.example.services.MembreService;

import java.util.List;
import java.util.Optional;

@Controller
public class MembreController {

    @Autowired
    MembreService membreService;
    @Autowired
    GroupeService groupeService;

    @GetMapping("/membre")
    public String membre(Model model) {
        List<Groupe> groupes = groupeService.getAllGroupes();
        model.addAttribute("groupes", groupes);
        return "membre";
    }

    @PostMapping("/membre-add")
    public String insertMembre(@ModelAttribute Membre membre, Model model) {
        membreService.saveMembre(membre);
        model.addAttribute("message", "Enregistrer ok");
        return "redirect:/membres";
    }

    @GetMapping("/membres")
    public String membres(Model model) {
        List<Membre> membres = membreService.getAllMembre();
        model.addAttribute("membres", membres);
        return "membres";
    }

    @GetMapping("/membre-update/{id}")
    public String getMembreUpdate(@PathVariable("id") Long id, Model model) {
        List<Groupe> groupes = groupeService.getAllGroupes();
        Membre membre = membreService.getMembreByIdMembre(id);
        if(membre != null && !CollectionUtils.isEmpty(groupes)) {
            model.addAttribute("membre", membre);
            model.addAttribute("groupes", groupes);
        }
        return  "membre-update";
    }

    @PutMapping("/membre-update-go/{id}")
    public String updateMembre(@PathVariable("id") Long id,@ModelAttribute Membre membre, Model model) {
        Membre membreExistant = membreService.getMembreByIdMembre(id);
        if(membreExistant != null) {
            membreExistant.setNom(membre.getNom());
            membreExistant.setPrenom(membre.getPrenom());
            membreExistant.setEmail(membre.getEmail());
            membreExistant.setAdresse(membre.getAdresse());
            membreExistant.setCodePostal(membre.getCodePostal());
            membreExistant.setVille(membre.getVille());
            if(membre.getGroupe() != null) {
                Optional<Groupe> groupeOptional = groupeService.getGroupeById(membre.getGroupe().getNumero());
                if(groupeOptional.isPresent()) {
                    Groupe groupe = groupeOptional.get();
                    membreExistant.setGroupe(groupe);
                }
            }
            membreExistant.setType(membre.getType());
            membreService.updateMembre(membreExistant);
        } else {
            System.out.println("Membre non existant");
        }

        return "redirect:/membres";
    }

    @GetMapping("/membre-delete/{id}")
    public String deleteMembre(@PathVariable("id") Long id,@ModelAttribute Membre membre, Model model) {
        Membre membreExistant = membreService.getMembreByIdMembre(id);
        if(membreExistant != null) {
            membreService.deleteMembre(membreExistant);
        } else {
            System.out.println("Membre non existant");
        }
        return "redirect:/membres";
    }


}
