package biblio.repository.adherant;

import biblio.entities.ExemplaireLivre;
import biblio.entities.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemplaireLivreRepository extends JpaRepository<ExemplaireLivre, Integer> {
    @Query("SELECT COUNT(e) FROM ExemplaireLivre e WHERE e.livre = :livre")
    long countByLivre(@Param("livre") Livre livre);
}
