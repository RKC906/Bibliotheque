package biblio.repository.adherant;

import biblio.entities.Authentification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthentificationRepository extends JpaRepository<Authentification, Integer> {
    Authentification findByEmailAndMotDePasse(String email, String motDePasse);
}