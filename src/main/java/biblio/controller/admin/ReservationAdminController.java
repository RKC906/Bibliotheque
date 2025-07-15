package biblio.controller.admin;


import biblio.repository.adherant.*;

import biblio.repository.admin.*;

import biblio.services.admin.*;

import biblio.repository.adherant.*;

import biblio.entities.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/admin/reservations")
public class ReservationAdminController {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private AdherantRepository adherantRepository;
    @Autowired
    private PenaliteRepository penaliteRepository;
    @Autowired
    private ReglePretRepository reglePretRepository;
    @Autowired
    private ExemplaireLivreRepository exemplaireLivreRepository;
    @Autowired
    private LivreRepository livreRepository;
    @Autowired
    private PretRepository pretRepository;
    @Autowired
    private CategorieLivreAssociationRepository categorieLivreAssociationRepository;
    @Autowired
    private ProfileCategorieLivreAssociationRepository profileCategorieLivreAssociationRepository;
    @Value("${pret.duree.jours:15}")
    private int dureePretJours;

    @Autowired
    private ReservationAdminService reservationAdminService;

    @GetMapping("")
    public String listeReservations(Model model) {
        List<Reservation> reservations = reservationRepository.findAll();
        model.addAttribute("reservations", reservations);
        // Pour chaque réservation, on récupère l'adhérant via l'exemplaire
        for (Reservation reservation : reservations) {
            ExemplaireLivre ex = reservation.getExemplaireLivre();
            Adherant adherant = null;
            if (ex != null && ex.getId_ExemplaireLivre() != null) {
                adherant = adherantRepository.findAdherantByReservationId(reservation.getIdReservation());
            }
            model.addAttribute("adherant_" + reservation.getIdReservation(), adherant);
        }
        return "admin/Reservations";
    }

    @PostMapping("/accepter")
    public String accepterReservation(@RequestParam("reservationId") Integer reservationId,RedirectAttributes redirectAttributes) 
    {
        String result = reservationAdminService.accepterReservation(reservationId);
        String messageType = result.startsWith("error") ? "error" : "success";
        redirectAttributes.addFlashAttribute(messageType, result);
        return "redirect:/admin/reservations";
    }

    @PostMapping("/refuser")
    public String refuserReservation(@RequestParam("reservationId") Integer reservationId,
            RedirectAttributes redirectAttributes) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation == null) {
            redirectAttributes.addFlashAttribute("error", "Réservation introuvable.");
            return "redirect:/admin/reservations";
        }
        Status statusRefuse = new Status();
        statusRefuse.setId_Status(3); // 3 = Refuser
        reservation.setStatusEntity(statusRefuse);
        reservationRepository.save(reservation);
        redirectAttributes.addFlashAttribute("success", "Réservation refusée.");
        return "redirect:/admin/reservations";
    }
}
