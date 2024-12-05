package org.example.controller;

import org.example.classes.Membre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import services.MembreService;

@Controller
public class Home {

    @Autowired
    LoginService loginService;
    @Autowired
    private MembreService membreService;

    @GetMapping("/")
    public String connexion() {
        return "connexion";
    }

    @GetMapping("/isOk")
    public String isOk(@ModelAttribute Membre membre, Model model) {
        Membre user = membreService.getMembreByNomAndPassword(membre.getNom(),membre.getPassword);
        if(user != null) {
            model.addAttribute("user", user);
            return "index";
        } else {
            model.addAttribute("error", "Invalid login or password");
            return "connexion";
        }
    }

    @GetMapping("/Home")
    public String index(){
        return "index";
    }

}
