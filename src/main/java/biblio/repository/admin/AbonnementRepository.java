package biblio.repository.admin;

import biblio.entities.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbonnementRepository extends JpaRepository<Abonnement, Integer> {
    
   @Query(value = "SELECT adh.nom,adh.prenom,abo.dateInscription,abo.DateFinInscription FROM Abonnement abo JOIN Adherant adh ON abo.adherant_idAdherant = adh.idAdherant", 
       nativeQuery = true)
    List<Abonnement> findAllWithAdherant();
}