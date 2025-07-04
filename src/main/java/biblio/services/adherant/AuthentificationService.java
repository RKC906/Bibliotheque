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
        // Recherche via la relation Authentification
        return adherantRepository.findByAuthentification(email, motDePasse);
    }

    public boolean emailExists(String email) {
        return adherantRepository.existsByAuthentificationEmail(email);
    }
}