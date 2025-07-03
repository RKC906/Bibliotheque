package biblio.controller;

import biblio.entities.Adherant;
import biblio.services.AuthentificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class AdherantController 
{
    @Autowired
    private AuthentificationService authentificationService;

    @PostMapping("/adherant/login")
    public String login(@RequestParam("email") String email, @RequestParam("motDePasse") String motDePasse, Model model) 
    {
        var adherant = authentificationService.login(email, motDePasse);
        if (adherant != null) 
        {
            model.addAttribute("adherant", adherant);
            return "homeAdherant";
        } else {
            model.addAttribute("error", "Email ou mot de passe incorrect");
            return "logAdherant";
        }
    }
}