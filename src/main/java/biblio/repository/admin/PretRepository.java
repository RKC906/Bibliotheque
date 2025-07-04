package biblio.repository.admin;

import biblio.entities.Pret;
import biblio.entities.Adherant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;

@Repository
public interface PretRepository extends JpaRepository<Pret, Integer> {
    long countByAdherantAndDateFinAfter(Adherant adherant, Date dateFin);
}
