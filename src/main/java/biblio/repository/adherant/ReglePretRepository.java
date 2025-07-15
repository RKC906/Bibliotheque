package biblio.repository.adherant;

import biblio.entities.ReglePret;
import biblio.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReglePretRepository extends JpaRepository<ReglePret, Integer> {
    ReglePret findByProfile(Profile profile);
}
