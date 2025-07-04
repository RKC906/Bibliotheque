package biblio.repository.adherant;

import biblio.entities.Adherant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdherantRepository extends JpaRepository<Adherant, Integer> {
    Adherant findByEmailAndMotDePasse(String email, String motDePasse);

    boolean existsByEmail(String email);

    // Trouver l'adhérant ayant réservé un exemplaire via la réservation
    @org.springframework.data.jpa.repository.Query("SELECT a FROM Adherant a WHERE a.idAdherant = (SELECT p.adherant.idAdherant FROM Pret p WHERE p.exemplaireLivre.idExemplaireLivre = (SELECT r.exemplaireLivre.idExemplaireLivre FROM Reservation r WHERE r.idReservation = :reservationId))")
    Adherant findAdherantByReservationId(
            @org.springframework.data.repository.query.Param("reservationId") Integer reservationId);
}