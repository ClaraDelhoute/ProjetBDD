package org.example.controller;

import jakarta.persistence.Entity;
import org.example.classes.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import services.LoginService;

@Controller
public class Home {

    @Autowired
    LoginService loginService;

    @GetMapping("/")
    public String connexion() {
        return "connexion";
    }

    @GetMapping("/isOk")
    public String isOk(@ModelAttribute Login login, Model model) {
        Login user = loginService.getLoginByloginAndPassword(login.getLogin(), login.getPassword());
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
