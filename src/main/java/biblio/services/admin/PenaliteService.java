package biblio.services.admin;

import biblio.entities.Penalite;
import biblio.entities.Admin;
import biblio.repository.admin.PenaliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Service
public class PenaliteService {
    @Autowired
    private PenaliteRepository penaliteRepository;

    public List<Penalite> getAllPenalites() {
        return penaliteRepository.findAll();
    }

    public Penalite creerPenalite(Penalite penalite, HttpSession session) {
        Integer adminId = (Integer) session.getAttribute("adminId");
        if (adminId != null) {
            Admin admin = new Admin();
            admin.setId_Admin(adminId);
            penalite.setAdmin(admin);
        }
        return penaliteRepository.save(penalite);
    }
}
