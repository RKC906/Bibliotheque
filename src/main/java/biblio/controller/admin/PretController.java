package biblio.controller.admin;

import biblio.repository.admin.RetourRepository;
import biblio.services.admin.PretService;
import biblio.services.admin.RetourService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/prets")
public class PretController {
    @Autowired
    private PretService pretService;
   
    @Autowired
    private RetourService retourService;

     @GetMapping("/liste")
    public String listPretsWithAdherants(Model model) {
        model.addAttribute("prets", pretService.getActivePretsWithAdherants());
        model.addAttribute("pretsRendus", retourService.getIdPretsRendus());
        return "admin/listePrets";
    }
}