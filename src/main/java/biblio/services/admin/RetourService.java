package biblio.services.admin;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import biblio.entities.Pret;
import biblio.entities.Retour;
import biblio.repository.admin.PretRepository;
import biblio.repository.admin.RetourRepository;

@Service
@Transactional
public class RetourService {

    private final RetourRepository retourRepository;
    private final PretRepository pretRepository;

    public RetourService(RetourRepository retourRepository, PretRepository pretRepository) {
        this.retourRepository = retourRepository;
        this.pretRepository = pretRepository;
    }

    public boolean enregistrerRetour(Integer idPret, Date dateRetour) {
        Optional<Pret> pretOpt = pretRepository.findById(idPret);
        if (pretOpt.isPresent()) {
            Retour retour = new Retour();
            retour.setPret(pretOpt.get());
            retour.setDate_(dateRetour);
            retourRepository.save(retour);
            return true;
        }
        return false; // prêt non trouvé
    }

    public Set<Integer> getIdPretsRendus() {
        return retourRepository.findAll()
                .stream()
                .map(retour -> retour.getPret().getIdPret())
                .collect(Collectors.toSet());
    }

}
