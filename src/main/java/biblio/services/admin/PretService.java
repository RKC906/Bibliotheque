package biblio.services.admin;

import biblio.entities.*;
import biblio.repository.admin.*;
import biblio.repository.adherant.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class PretService {
    @Autowired
    private PretRepository pretRepository;
    
    @Autowired
    private ReglePretRepository reglePretRepository;
    
    @Autowired
    private PenaliteRepository penaliteRepository;
    
    @Value("${pret.duree.jours:15}")
    private int dureePretJours;

    @Transactional
    public Pret creerPret(Adherant adherant, ExemplaireLivre exemplaire, TypePret typePret) {
        Pret pret = new Pret();
        pret.setAdherant(adherant);
        pret.setExemplaireLivre(exemplaire);
        pret.setTypePret(typePret);
        pret.setDateDebut(new Date());
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, dureePretJours);
        pret.setDateFin(cal.getTime());
        
        return pretRepository.save(pret);
    }

    public boolean isAdherantPenalise(Adherant adherant) {
        return penaliteRepository.existsByAdherantAndDateFinAfter(adherant, new Date());
    }

    public boolean hasReachedMaxPrets(Adherant adherant) {
        ReglePret regle = reglePretRepository.findByProfile(adherant.getProfile());
        int maxLivres = regle != null ? regle.getNbrPretLivre() : 0;
        long nbPrets = pretRepository.countByAdherantAndDateFinAfter(adherant, new Date());
        return nbPrets >= maxLivres;
    }

   public List<Pret> getActivePretsWithAdherants() {
        return pretRepository.findAllActivePretsWithRelations();
    }

    public List<Pret> getPretsNonRendus() {
    return pretRepository.findPretsNonRendus(); // méthode qu'on va créer dans le repository
}

}