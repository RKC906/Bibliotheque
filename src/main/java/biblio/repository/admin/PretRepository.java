package biblio.repository.admin;

import biblio.entities.Pret;
import biblio.entities.Adherant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PretRepository extends JpaRepository<Pret, Integer> {
    long countByAdherantAndDateFinAfter(Adherant adherant, Date dateFin);
    
    @Query("SELECT p FROM Pret p JOIN FETCH p.adherant LEFT JOIN FETCH p.admin WHERE p.dateFin > CURRENT_DATE")
    List<Pret> findAllActivePretsWithRelations();

    @Query("SELECT p FROM Pret p WHERE p.idPret NOT IN (SELECT r.pret.idPret FROM Retour r)")
    List<Pret> findPretsNonRendus();

}