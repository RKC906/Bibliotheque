package biblio.repository.adherant;

import biblio.entities.CategorieLivreAssociation;
import biblio.entities.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategorieLivreAssociationRepository extends JpaRepository<CategorieLivreAssociation, Integer> {
    @Query("SELECT c.categorieLivre.nom FROM CategorieLivreAssociation c WHERE c.livre = :livre")
    List<String> findCategorieNomsByLivre(@Param("livre") Livre livre);
}
