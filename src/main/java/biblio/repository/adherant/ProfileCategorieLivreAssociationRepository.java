package biblio.repository.adherant;

import biblio.entities.ProfileCategorieLivreAssociation;
import biblio.entities.Profile;
import biblio.entities.CategorieLivre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileCategorieLivreAssociationRepository
        extends JpaRepository<ProfileCategorieLivreAssociation, Integer> {
    @Query("SELECT pcla.categorieLivre FROM ProfileCategorieLivreAssociation pcla WHERE pcla.profile = :profile")
    List<CategorieLivre> findCategoriesByProfile(@Param("profile") Profile profile);
}
