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

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "admin/loginAdmin";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("motDePasse") String motDePasse, Model model) {
        Admin admin = adminService.login(email, motDePasse);
        if (admin != null) {
            // Authentification réussie, rediriger vers la page d'accueil admin (à adapter)
            model.addAttribute("admin", admin);
            return "admin/homeAdmin";
        } else {
            model.addAttribute("error", "Email ou mot de passe incorrect");
            return "admin/loginAdmin";
        }
    }
}
