package org.example.controller;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.Entity;
import org.example.classes.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.LoginService;

@Controller
public class Home {

    @Autowired
    LoginService loginService;

    @GetMapping("/")
    public String connexion() {
        return "connexion";
    }

    @PostMapping("/isOk")
    public String isOk(@ModelAttribute Login login, Model model) {
        Login user = loginService.getLoginByUsernameAndPassword(login.getUsername(), login.getPassword());
        if(user != null) {
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
    public String addAccount(@ModelAttribute Login login, Model model) {
        if (StringUtils.isNotBlank(login.getUsername()) && StringUtils.isNotBlank(login.getPassword())) {
            if (StringUtils.isNotBlank(login.getConfirmPassword()) && login.getPassword().equals(login.getConfirmPassword())) {
                loginService.saveLogin(login);
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
