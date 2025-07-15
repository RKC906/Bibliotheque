package biblio.repository.admin;

import biblio.entities.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbonnementRepository extends JpaRepository<Abonnement, Integer> {
    
  @Query("SELECT a FROM Abonnement a JOIN FETCH a.adherant")
List<Abonnement> findAllWithAdherant();
}