package org.example.controller;

import org.example.classes.Groupe;
import org.example.classes.Materiel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import services.GroupeService;
import services.MaterielService;

import java.util.List;
import java.util.Optional;

@Controller
public class GroupeController {

    @Autowired
    private GroupeService groupeService;
    @Autowired
    private MaterielService materielService;

    @GetMapping("/groupe")
    public String groupe(Model model) {
        List<Materiel> materiels = materielService.getAllMateriel();
        if(!CollectionUtils.isEmpty(materiels)) {
            model.addAttribute("materiels", materiels);
        }
        return "groupe";
    }

    @PostMapping("/groupe-add")
    public String insertGroupe(@ModelAttribute("groupe") Groupe groupe) {
        groupeService.addGroupe(groupe);
        return "redirect:/groupes";
    }

    @GetMapping("/groupes")
    public String getAllGroupes(Model model) {
        List<Groupe> groupes = groupeService.getAllGroupes();
        model.addAttribute("groupes", groupes);
        return "groupes";
    }

    @GetMapping("/groupe-delete/{id}")
    public String deleteGroupe(@PathVariable("id") String id, @ModelAttribute Groupe groupe, Model model) {
        Optional<Groupe> groupeExistant = groupeService.getGroupeById(id);
        if(groupeExistant.isPresent()) {
            Groupe groupeExist = groupeExistant.get();
            groupeService.deleteGroupe(groupeExist);
        } else {
            System.out.println("Groupe non existant");
        }
        return "redirect:/groupes";
    }

    @GetMapping("groupe-update/{id}")
    public String getUpdatedGroupe(@PathVariable("id") String id, Model model) {

        Optional<Groupe> groupeExistant = groupeService.getGroupeById(id);
        if(groupeExistant.isPresent()) {
            Groupe groupeExist = groupeExistant.get();
            model.addAttribute("groupe", groupeExist);
        }
        return "groupe-update";
    }

    @PutMapping("groupe-update-go/{id}")
    public String updateGroupe(@ModelAttribute("groupe") Groupe groupe, @PathVariable("id") String id) {
        Optional<Groupe> groupeExistant = groupeService.getGroupeById(id);
        if(groupeExistant.isPresent()) {
            Groupe groupeExist = groupeExistant.get();
            groupeExist.setNom(groupe.getNom());
            groupeExist.setVille(groupe.getVille());
            groupeExist.setCodePostal(groupe.getCodePostal());
            if(groupe.getMateriel() != null) {
                Materiel materiel = groupe.getMateriel();
                groupeExist.setMateriel(materiel);
            }
            groupeService.updateGroupe(groupeExist);


        }
        return "redirect:/groupes";
    }
}
