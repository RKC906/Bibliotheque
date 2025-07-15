package biblio.repository.admin;

import biblio.entities.Penalite;
import biblio.entities.Adherant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;

@Repository
public interface PenaliteRepository extends JpaRepository<Penalite, Integer> {
    boolean existsByAdherantAndDateFinAfter(Adherant adherant, Date dateFin);
}
