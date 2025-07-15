package biblio.repository.adherant;

import biblio.entities.Profile;
import biblio.entities.CategorieLivre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileCategorieLivreRepository extends JpaRepository<CategorieLivre, Integer> {
    @Query("SELECT c FROM CategorieLivre c JOIN ProfileCategorieLivreAssociation pcla ON c.id_CategorieLivre = pcla.categorieLivre.id_CategorieLivre WHERE pcla.profile = :profile")
    List<CategorieLivre> findCategoriesByProfile(@Param("profile") Profile profile);
}
