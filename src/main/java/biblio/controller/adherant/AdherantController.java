package biblio.controller.adherant;

import biblio.entities.Adherant;
import biblio.services.adherant.AuthentificationService;
import biblio.services.adherant.LivreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Controller
public class AdherantController {

    @Autowired
    private AuthentificationService authentificationService;

    @Autowired
    private LivreService livreService;

    @PostMapping("/adherant/login")
    public String login(@RequestParam("email") String email, @RequestParam("motDePasse") String motDePasse,
            Model model) {
        var adherant = authentificationService.login(email, motDePasse);
        if (adherant != null) {
            model.addAttribute("adherant", adherant);
            model.addAttribute("livres", livreService.getAllLivreDetails());
            model.addAttribute("auteurs", livreService.getAllAuteurs());
            model.addAttribute("categories", livreService.getAllCategories());
            return "adherant/homeAdherant";
        } else {
            model.addAttribute("error", "Email ou mot de passe incorrect");
            return "adherant/logAdherant";
        }
    }

    @GetMapping("adherant/livres")
    public String getLivres(
            @RequestParam(value = "auteur", required = false) Integer auteurId,
            @RequestParam(value = "langue", required = false) String langue,
            @RequestParam(value = "dateDebut", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateDebut,
            @RequestParam(value = "dateFin", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFin,
            @RequestParam(value = "nbPagesMin", required = false) Integer nbPagesMin,
            @RequestParam(value = "nbPagesMax", required = false) Integer nbPagesMax,
            @RequestParam(value = "categorie", required = false) Integer categorieId,
            @RequestParam(value = "nbExMin", required = false) Integer nbExMin,
            @RequestParam(value = "nbExMax", required = false) Integer nbExMax,
            Model model) {
        model.addAttribute("livres", livreService.getFilteredLivreDetails(auteurId, langue, dateDebut, dateFin,
                nbPagesMin, nbPagesMax, categorieId, nbExMin, nbExMax));
        model.addAttribute("auteurs", livreService.getAllAuteurs());
        model.addAttribute("categories", livreService.getAllCategories());
        return "adherant/homeAdherant";
    }
}