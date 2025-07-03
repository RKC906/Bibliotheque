package biblio.services.admin;

import biblio.entities.Admin;
import biblio.repository.admin.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Admin login(String email, String motDePasse) {
        return adminRepository.findByEmailAndMotDePasse(email, motDePasse);
    }

    public Admin findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }
}
