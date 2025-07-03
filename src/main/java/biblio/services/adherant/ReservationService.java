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

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation creerReservation(Reservation reservation) {
        // Par défaut, statusEntity id = 1 (En Cours), status = "En Cours"
        Status status = new Status();
        status.setId_Status(1);
        reservation.setStatusEntity(status);
        reservation.setStatus("En Cours");
        return reservationRepository.save(reservation);
    }
}
