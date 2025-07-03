package biblio.repository.admin;

import biblio.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByEmailAndMotDePasse(String email, String motDePasse);

    Admin findByEmail(String email);
}
