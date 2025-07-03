package biblio.controller.admin;

import biblio.entities.Admin;
import biblio.services.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private biblio.services.admin.AdherantService adherantService;

    @Autowired
    private biblio.services.admin.AbonnementService abonnementService;

    @Autowired
    private biblio.services.admin.PenaliteService penaliteService;

    @GetMapping("/abonnement")
    public String abonnementPage(org.springframework.ui.Model model) {
        model.addAttribute("abonnements", abonnementService.getAllAbonnements());
        return "admin/Abonnement";
    }

    @GetMapping("/abonnement/nouveau")
    public String formNouveauAbonnement(org.springframework.ui.Model model) {
        model.addAttribute("adherants", adherantService.getAllAdherants());
        return "admin/NouvelAbonnement";
    }

    @PostMapping("/abonnement/nouveau")
    public String creerAbonnement(
            @RequestParam("adherantId") Integer adherantId,
            @RequestParam("dateInscription") String dateInscription,
            @RequestParam("dateFinInscription") String dateFinInscription,
            jakarta.servlet.http.HttpSession session) {
        biblio.entities.Abonnement abonnement = new biblio.entities.Abonnement();
        biblio.entities.Adherant adherant = new biblio.entities.Adherant();
        adherant.setId_Adherant(adherantId);
        abonnement.setAdherant(adherant);
        abonnement.setDate_inscription(java.sql.Date.valueOf(dateInscription));
        abonnement.setDate_fin_inscription(java.sql.Date.valueOf(dateFinInscription));
        abonnementService.creerAbonnement(abonnement, session);
        return "redirect:/admin/Abonnement"; // Redirect to the abonnement page
    }

    @Autowired
    private AdminService adminService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "admin/loginAdmin";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("motDePasse") String motDePasse, Model model,
            HttpSession session) {
        Admin admin = adminService.login(email, motDePasse);
        if (admin != null) {
            // Stocker l'admin en session
            session.setAttribute("adminId", admin.getId_Admin());
            model.addAttribute("admin", admin);
            return "admin/homeAdmin";
        } else {
            model.addAttribute("error", "Email ou mot de passe incorrect");
            return "admin/loginAdmin";
        }
    }

     @GetMapping("/penalite")
    public String penalitePage(org.springframework.ui.Model model) {
        model.addAttribute("penalites", penaliteService.getAllPenalites());
        return "admin/Penalite";
    }

    @GetMapping("/penalite/nouveau")
    public String formNouvellePenalite(org.springframework.ui.Model model) {
        model.addAttribute("adherants", adherantService.getAllAdherants());
        return "admin/NouvellePenalite";
    }

    @PostMapping("/penalite/nouveau")
    public String creerPenalite(
            @RequestParam("adherantId") Integer adherantId,
            @RequestParam("dateDebut") String dateDebut,
            @RequestParam("dateFin") String dateFin,
            jakarta.servlet.http.HttpSession session) {
        biblio.entities.Penalite penalite = new biblio.entities.Penalite();
        biblio.entities.Adherant adherant = new biblio.entities.Adherant();
        adherant.setId_Adherant(adherantId);
        penalite.setAdherant(adherant);
        penalite.setDate_debut(java.sql.Date.valueOf(dateDebut));
        penalite.setDate_fin(java.sql.Date.valueOf(dateFin));
        penaliteService.creerPenalite(penalite, session);
        return "redirect:/admin/penalite";
    }
}
