package biblio.repository.adherant;

import biblio.entities.StatusAdherant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusAdherantRepository extends JpaRepository<StatusAdherant, Integer> {
}
