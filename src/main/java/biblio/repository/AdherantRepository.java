package biblio.repository;

import biblio.entities.Adherant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdherantRepository extends JpaRepository<Adherant, Integer> {
    Adherant findByAuthentificationEmailAndAuthentificationMotDePasse(String email, String motDePasse);
    boolean existsByAuthentificationEmail(String email);
}