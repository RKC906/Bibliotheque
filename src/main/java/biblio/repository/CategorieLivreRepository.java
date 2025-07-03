package biblio.repository;

import biblio.entities.CategorieLivre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieLivreRepository extends JpaRepository<CategorieLivre, Integer> {
}
