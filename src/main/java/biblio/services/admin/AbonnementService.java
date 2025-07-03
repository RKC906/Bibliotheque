
package biblio.services.admin;

import biblio.entities.Admin;
import jakarta.servlet.http.HttpSession;

import biblio.entities.Abonnement;
import biblio.repository.admin.AbonnementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbonnementService {
    @Autowired
    private AbonnementRepository abonnementRepository;

    public List<Abonnement> getAllAbonnements() {
        return abonnementRepository.findAll();
    }

    public Abonnement creerAbonnement(Abonnement abonnement, HttpSession session) {
        // Récupérer l'admin connecté depuis la session
        Integer adminId = (Integer) session.getAttribute("adminId");
        if (adminId != null) {
            Admin admin = new Admin();
            admin.setId_Admin(adminId);
            abonnement.setAdmin(admin);
        }
        return abonnementRepository.save(abonnement);
    }
}
