package biblio.controller.admin;

import biblio.entities.Pret;
import biblio.entities.TypePret;
import biblio.entities.Admin;
import biblio.entities.Adherant;
import biblio.entities.ExemplaireLivre;
import biblio.services.admin.PretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/pret")
public class PretController {
    @Autowired
    private PretService pretService;

    @GetMapping("")
    public String listPrets(Model model) {
        List<Pret> prets = pretService.getAllPrets();
        model.addAttribute("prets", prets);
        return "admin/Prets";
    }
    // Ajoute ici les méthodes pour créer, éditer, supprimer un prêt si besoin
}
