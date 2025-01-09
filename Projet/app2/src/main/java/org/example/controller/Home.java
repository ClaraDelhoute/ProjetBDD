package org.example.controller;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpSession;
import org.example.classes.Membre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.example.services.MembreService;

@Controller
public class Home {


    @Autowired
    private MembreService membreService;

    @GetMapping("/")
    public String connexion() {
        return "connexion";
    }

    @PostMapping("/isOk")
    public String isOk(@RequestParam("username") String username, @RequestParam("password")String password, Model model, HttpSession session) {
        Membre user = membreService.getMembreByNomAndPassword(username, password);
        if(user != null) {
            session.setAttribute("user",user);
            model.addAttribute("user", user);
            return "index";
        } else {
            model.addAttribute("error", "Invalid login or password");
            return "connexion";
        }
    }

    @GetMapping("/createAccount")
    public String createAccount(Model model) {
        return "create-compte";
    }

    @PostMapping("/addAccount")
    public String addAccount(@ModelAttribute Membre membre, Model model) {
        if (StringUtils.isNotBlank(membre.getNom()) && StringUtils.isNotBlank(membre.getPassword())) {
            if (StringUtils.isNotBlank(membre.getConfirmPassword()) && membre.getPassword().equals(membre.getConfirmPassword())) {
                membreService.saveMembre(membre);
                return "connexion";
            } else {
                model.addAttribute("error", "Passwords do not match");
                return "create-compte";
            }
        }
        model.addAttribute("error", "Login and password cannot be blank");
        return "create-compte";
    }

    @GetMapping("/Home")
    public String index(){
        return "index";
    }

}
