package biblio.controller.adherant;

import biblio.entities.Adherant;
import biblio.entities.ExemplaireLivre;
import biblio.entities.Reservation;
import biblio.entities.TypePret;
import biblio.services.adherant.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import java.util.List;

import biblio.repository.adherant.LivreRepository;
import biblio.repository.adherant.ExemplaireLivreRepository;
import biblio.repository.adherant.TypePretRepository;

@Controller
@RequestMapping("/adherant/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private LivreRepository livreRepository;
    @Autowired
    private ExemplaireLivreRepository exemplaireLivreRepository;
    @Autowired
    private TypePretRepository typePretRepository;

    @GetMapping("/nouveau")
    public String formNouvelleReservation(@RequestParam("livreId") Integer livreId, Model model, HttpSession session) {
        // Vérification de la session
        Integer adherantId = (Integer) session.getAttribute("adherantId");
        if (adherantId == null) {
            return "redirect:/adherant/login";
        }

        List<ExemplaireLivre> exemplaires = exemplaireLivreRepository.findByLivreId_Livre(livreId);
        model.addAttribute("exemplaires", exemplaires);
        model.addAttribute("livreId", livreId);
        model.addAttribute("typesPret", typePretRepository.findAll());
        return "adherant/NouvelleReservation";
    }

     @PostMapping("/nouveau")
    public String creerReservation(
            @RequestParam("exemplaireId") Integer exemplaireId,
            @RequestParam("dateReservation") String dateReservation,
            @RequestParam("typePretId") Integer typePretId,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        
        // Récupération de l'ID adhérent depuis la session
        Integer adherantId = (Integer) session.getAttribute("adherantId");
        if (adherantId == null) {
            redirectAttributes.addFlashAttribute("error", "Veuillez vous connecter");
            return "redirect:/adherant/login";
        }

        try {
            // Création des objets nécessaires
            ExemplaireLivre exemplaire = exemplaireLivreRepository.findById(exemplaireId)
                .orElseThrow(() -> new IllegalArgumentException("Exemplaire non trouvé"));
            
            TypePret typePret = typePretRepository.findById(typePretId)
                .orElseThrow(() -> new IllegalArgumentException("Type de prêt non trouvé"));

            // Création de la réservation
            Reservation reservation = new Reservation();
            reservation.setExemplaireLivre(exemplaire);
            reservation.setDateReservation(dateReservation);
            reservation.setTypePret(typePret);
            
            // Ajout de l'adhérent
            Adherant adherant = new Adherant();
            adherant.setIdAdherant(adherantId);
            reservation.setAdherant(adherant);

            // Enregistrement via le service
            reservationService.creerReservation(reservation);
            
            redirectAttributes.addFlashAttribute("success", "Réservation créée avec succès");
            return "redirect:/adherant/livres";
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la création: " + e.getMessage());
            return "redirect:/adherant/livres";
        }
    }
}