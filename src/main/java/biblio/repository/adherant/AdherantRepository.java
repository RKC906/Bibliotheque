package biblio.repository.adherant;

import biblio.entities.Adherant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

@Repository
public interface AdherantRepository extends JpaRepository<Adherant, Integer> {

    @org.springframework.data.jpa.repository.Query("""
        SELECT a FROM Adherant a
        WHERE a.authentification.email = :email
        AND a.authentification.motDePasse = :motDePasse
    """)
    Adherant findByAuthentification(
        @Param("email") String email,
        @Param("motDePasse") String motDePasse
    );

    @org.springframework.data.jpa.repository.Query("""
        SELECT COUNT(a) > 0 FROM Adherant a
        WHERE a.authentification.email = :email
    """)
    boolean existsByAuthentificationEmail(@Param("email") String email);

    @org.springframework.data.jpa.repository.Query("""
        SELECT p.adherant FROM Pret p
        WHERE p.exemplaireLivre.id_ExemplaireLivre = (
            SELECT r.exemplaireLivre.id_ExemplaireLivre FROM Reservation r WHERE r.idReservation = :reservationId
        )
    """)
    Adherant findAdherantByReservationId(@Param("reservationId") Integer reservationId);
}