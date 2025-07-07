package biblio.services.admin;

import biblio.entities.*;
import biblio.repository.adherant.*;
import biblio.repository.admin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

@Service
public class ReservationAdminService {
    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private AdherantRepository adherantRepository;
    
    @Autowired
    private ExemplaireLivreRepository exemplaireLivreRepository;
    
    @Autowired
    private PretService pretService;
    
    @Autowired
    private CategorieLivreAssociationRepository categorieLivreAssociationRepository;
    
    @Autowired
    private ProfileCategorieLivreAssociationRepository profileCategorieLivreAssociationRepository;

    @Transactional
    public String accepterReservation(Integer reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
            .orElseThrow(() -> new IllegalArgumentException("Réservation introuvable."));

        // Récupération directe de l'adhérent depuis la réservation
        Adherant adherant = reservation.getAdherant();
        
        if (adherant == null) {
            return "Aucun adhérant associé à cette réservation.";
        }

        // 1. Vérification pénalité
        if (pretService.isAdherantPenalise(adherant)) {
            return "L'utilisateur est pénalisé.";
        }

        // 2. Vérification nombre de prêts
        if (pretService.hasReachedMaxPrets(adherant)) {
            return "L'utilisateur a atteint le nombre maximal de livres empruntés.";
        }

        // 3. Vérification statut adhérent
        if (adherant.getStatusAdherant() == null || 
            !Objects.equals(adherant.getStatusAdherant().getIdStatusAdherant(), 1)) {
            return "Le statut de l'adhérant n'est pas actif.";
        }

        // 4. Vérification abonnement
        if (adherant.getDateFinAbonnement() == null || 
            adherant.getDateFinAbonnement().before(new Date())) {
            return "L'abonnement de l'adhérant n'est plus valide.";
        }

        // 5. Vérification exemplaire
        ExemplaireLivre exemplaire = reservation.getExemplaireLivre();
        if (exemplaire == null || 
            exemplaireLivreRepository.findById(exemplaire.getId_ExemplaireLivre()).isEmpty()) {
            return "Exemplaire non disponible.";
        }

        // 6. Vérification compatibilité profil/catégorie
        if (!isProfilCompatible(adherant, exemplaire)) {
            return "Le profil de l'adhérant n'est pas compatible avec la catégorie du livre.";
        }

        // Accepter la réservation
        Status statusAccepte = new Status();
        statusAccepte.setId_Status(2);
        reservation.setStatusEntity(statusAccepte);
        reservationRepository.save(reservation);

        // Créer le prêt
        pretService.creerPret(adherant, exemplaire, reservation.getTypePret());

        return "Réservation acceptée.";
    }

    private boolean isProfilCompatible(Adherant adherant, ExemplaireLivre exemplaire) {
        Livre livre = exemplaire.getLivre();
        if (livre == null) return false;

        List<String> categoriesLivre = categorieLivreAssociationRepository.findCategorieNomsByLivre(livre);
        List<CategorieLivre> categoriesProfil = profileCategorieLivreAssociationRepository
                .findCategoriesByProfile(adherant.getProfile());

        Set<String> nomsCategoriesProfil = new HashSet<>();
        for (CategorieLivre cat : categoriesProfil) {
            nomsCategoriesProfil.add(cat.getNom());
        }

        return categoriesLivre.stream().anyMatch(nomsCategoriesProfil::contains);
    }
}