package biblio.services.adherant;

import biblio.entities.Adherant;
import biblio.repository.adherant.AdherantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationService {
    @Autowired
    private AdherantRepository adherantRepository;

    public Adherant login(String email, String motDePasse) {
        return adherantRepository.findByEmailAndMotDePasse(email, motDePasse);
    }

    public boolean emailExists(String email) {
        return adherantRepository.existsByEmail(email);
    }
}