package biblio.services.adherant;

import biblio.entities.Reservation;
import biblio.entities.Status;
import biblio.entities.ExemplaireLivre;
import biblio.entities.TypePret;
import biblio.repository.adherant.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation creerReservation(Reservation reservation) {
        // Validation minimale
        if (reservation.getAdherant() == null || reservation.getAdherant().getIdAdherant() == null) {
            throw new IllegalArgumentException("L'adhérent est requis pour une réservation");
        }
        
        if (reservation.getExemplaireLivre() == null || reservation.getExemplaireLivre().getId_ExemplaireLivre() == null) {
            throw new IllegalArgumentException("L'exemplaire est requis pour une réservation");
        }

        // Configuration du statut
        Status status = new Status();
        status.setId_Status(1); // 1 = En Cours
        reservation.setStatusEntity(status);
        reservation.setStatus("En Cours");

        return reservationRepository.save(reservation);
    }
}
