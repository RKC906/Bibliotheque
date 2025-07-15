package biblio.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import biblio.services.admin.*;

@Controller
public class RetourController {

    private final RetourService retourService;

    public RetourController(RetourService retourService) {
        this.retourService = retourService;
    }

    @PostMapping("/admin/traiterRetour")
    public String traiterRetour(
        @RequestParam("idPret") Integer idPret,
        @RequestParam("dateRetour") String dateRetourStr,
        RedirectAttributes redirectAttributes) {
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateRetour = sdf.parse(dateRetourStr);
            boolean ok = retourService.enregistrerRetour(idPret, dateRetour);
            if (ok) {
                redirectAttributes.addFlashAttribute("message", "Retour enregistré avec succès !");
            } else {
                redirectAttributes.addFlashAttribute("error", "Prêt non trouvé.");
            }
        } catch (ParseException e) {
            redirectAttributes.addFlashAttribute("error", "Date invalide.");
        }

        return "admin/listePrets";
    }

   @GetMapping("/admin/rendre")
public String FormRetour(@RequestParam("idPret") Integer idPret, Model model) {
    model.addAttribute("idPret", idPret);
    return "admin/RetourForm"; // correspond à /WEB-INF/views/admin/RetourForm.jsp
}


}
