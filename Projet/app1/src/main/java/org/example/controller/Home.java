package org.example.controller;

import io.micrometer.common.util.StringUtils;
import org.example.classes.Membre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
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
    public String isOk(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session) {
        Membre user = membreService.getMembreByNomAndPassword(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            // Redirection vers le mapping /dashboard pour effectuer le traitement associé
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid login or password");
            return "connexion";
        }
    }


}
