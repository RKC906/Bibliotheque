package biblio.controller.adherant;

import biblio.entities.ExemplaireLivre;
import biblio.entities.Reservation;
import biblio.services.adherant.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String formNouvelleReservation(@RequestParam("livreId") Integer livreId, Model model) {
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
            @RequestParam("typePretId") Integer typePretId) {
        Reservation reservation = new Reservation();
        ExemplaireLivre exemplaire = new ExemplaireLivre();
        exemplaire.setId_ExemplaireLivre(exemplaireId);
        reservation.setExemplaireLivre(exemplaire);
        reservation.setDateReservation(dateReservation);
        biblio.entities.TypePret typePret = new biblio.entities.TypePret();
        typePret.setId_TypePret(typePretId);
        reservation.setTypePret(typePret);
        reservationService.creerReservation(reservation);
        return "redirect:/adherant/livres";
    }
}
