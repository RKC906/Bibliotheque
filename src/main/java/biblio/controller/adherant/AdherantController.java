package biblio.controller.adherant;

import biblio.entities.Adherant;
import biblio.entities.Livre;
import biblio.services.adherant.AuthentificationService;
import biblio.services.adherant.LivreService;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AdherantController {

    @Autowired
    private AuthentificationService authentificationService;

    @Autowired
    private LivreService livreService;

    @PostMapping("/adherant/login")
    public String login(@RequestParam("email") String email, @RequestParam("motDePasse") String motDePasse,Model model,HttpSession session) 
    {
        var adherant = authentificationService.login(email, motDePasse);
        if (adherant != null) 
        {
            session.setAttribute("adherantId", adherant.getIdAdherant());
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

    @GetMapping("/adherant/livres/details/{id}")
    public String showDetails(@PathVariable("id") Integer id, Model model, HttpSession session) {
        if (session.getAttribute("adherantId") == null) {
            return "redirect:/adherant/logAdherant";
        }
        model.addAttribute("livreId", id);
        return "adherant/LivresDetails";
    }

    @GetMapping("/api/adherant/livres/details/{id}")
    @ResponseBody
    public ResponseEntity<?> getDetailsApi(@PathVariable("id") Integer id, HttpSession session) {
        if (session.getAttribute("adherantId") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        try {
            Livre livre = livreService.getLivreById(id);
            if (livre == null) {
                return ResponseEntity.notFound().build();
            }
            Map<String, Object> response = new HashMap<>();
            response.put("livre", livre);
            response.put("categories", livreService.getCategories(livre));
            response.put("nbExemplaires", livreService.getNombreExemplaires(livre));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}