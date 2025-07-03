package biblio.services.admin;

import biblio.entities.Adherant;
import biblio.entities.StatusAdherant;
import biblio.repository.adherant.AdherantRepository;
import biblio.repository.adherant.StatusAdherantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdherantStatusService {
    @Autowired
    private AdherantRepository adherantRepository;
    @Autowired
    private StatusAdherantRepository statusAdherantRepository;

    /**
     * Met à jour le status de tous les adhérants selon la validité de leur
     * abonnement.
     * Si la date de fin d'abonnement est >= aujourd'hui => Actif (1), sinon Inactif
     * (2)
     */
    public void updateAllAdherantStatus(List<Adherant> adherants) {
        StatusAdherant actif = statusAdherantRepository.findById(1).orElse(null);
        StatusAdherant inactif = statusAdherantRepository.findById(2).orElse(null);
        Date now = new Date();
        for (Adherant adherant : adherants) {
            Date dateFin = adherant.getDateFinAbonnement(); // à implémenter dans Adherant si besoin
            if (dateFin != null && !dateFin.before(now)) {
                adherant.setStatusAdherant(actif);
            } else {
                adherant.setStatusAdherant(inactif);
            }
            adherantRepository.save(adherant);
        }
    }
}
