package biblio.repository.adherant;

import biblio.entities.ExemplaireLivre;
import biblio.entities.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExemplaireLivreRepository extends JpaRepository<ExemplaireLivre, Integer> {
    @Query("SELECT COUNT(e) FROM ExemplaireLivre e WHERE e.livre = :livre")
    long countByLivre(@Param("livre") Livre livre);

    @Query("SELECT e FROM ExemplaireLivre e WHERE e.livre.id_Livre = :livreId")
    List<ExemplaireLivre> findByLivreId_Livre(@Param("livreId") Integer livreId);
}
