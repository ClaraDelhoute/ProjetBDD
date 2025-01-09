package org.example.controller;

import org.example.classes.Groupe;
import org.example.classes.Materiel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.example.services.GroupeService;
import org.example.services.MaterielService;

import java.util.List;
import java.util.Optional;

@Controller
public class MaterielController {
    @Autowired
    private MaterielService materielService;
    @Autowired
    private GroupeService groupeService;

    @GetMapping("/materiel")
    public String materiel(Model model) {
        List<Groupe> groupes = groupeService.getAllGroupes();
        if(!CollectionUtils.isEmpty(groupes)){
            model.addAttribute("groupes", groupes);
        }
        return "materiel";
    }

    @PostMapping("/add-materiel")
    public String addMateriel(@ModelAttribute Materiel materiel) {
        materielService.insertMateriel(materiel);
        return "redirect:/materiels";
    }

    @PutMapping("/materiel-update")
    public String updateMateriel(@PathVariable String id,Model model){
        Optional<Materiel> materiel = materielService.getMateriel(id);
        if(materiel.isPresent()){
            Materiel m = materiel.get();
            model.addAttribute("materiel", m);
        }
        return "update-materiel";
    }

    @GetMapping("/materiel-update-go")
    public String updateMaterielGo(@PathVariable String id, @ModelAttribute Materiel materiel,Model model){
        Optional<Materiel> optionalMateriel = materielService.getMateriel(id);
        if(optionalMateriel.isPresent()){
            Materiel m = optionalMateriel.get();
            m.setPrix(materiel.getPrix());
            m.setGroupe(materiel.getGroupe());
            m.setModele(materiel.getModele());
            m.setType(materiel.getType());
            m.setNumeroSerie(materiel.getNumeroSerie());
            m.setMarque(materiel.getMarque());
            materielService.updateMateriel(m);
        }
        return "redirect:/materiels";
    }



    @GetMapping("/materiels")
    public String materiels(Model model) {
       List<Materiel> materiels =  materielService.getAllMateriel();
       model.addAttribute("materiels", materiels);
       return "materiels";
    }

}
