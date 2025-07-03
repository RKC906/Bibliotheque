package biblio.services;

import biblio.entities.Adherant;
import biblio.repository.AdherantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationService {
    @Autowired
    private AdherantRepository adherantRepository;

    public Adherant login(String email, String motDePasse) {
        return adherantRepository.findByAuthentificationEmailAndAuthentificationMotDePasse(email, motDePasse);
    }

    public boolean emailExists(String email) {
        return adherantRepository.existsByAuthentificationEmail(email);
    }
}