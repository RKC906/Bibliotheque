package biblio.controller.admin;

import biblio.entities.Reservation;
import biblio.entities.ExemplaireLivre;
import biblio.entities.Livre;
import biblio.entities.Adherant;
import biblio.repository.adherant.ReservationRepository;
import biblio.repository.adherant.AdherantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/reservations")
public class ReservationAdminController {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private AdherantRepository adherantRepository;

    @GetMapping("")
    public String listeReservations(Model model) {
        List<Reservation> reservations = reservationRepository.findAll();
        model.addAttribute("reservations", reservations);
        // Pour chaque réservation, on récupère l'adhérant via l'exemplaire
        for (Reservation reservation : reservations) {
            ExemplaireLivre ex = reservation.getExemplaireLivre();
            Adherant adherant = null;
            if (ex != null && ex.getId_ExemplaireLivre() != null) {
                adherant = adherantRepository.findAdherantByReservationId(reservation.getId_Reservation());
            }
            model.addAttribute("adherant_" + reservation.getId_Reservation(), adherant);
        }
        return "admin/Reservations";
    }
}
