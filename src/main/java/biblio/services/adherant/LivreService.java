package biblio.services.adherant;

import biblio.entities.Livre;
import biblio.repository.adherant.AuteurRepository;
import biblio.repository.adherant.CategorieLivreAssociationRepository;
import biblio.repository.adherant.CategorieLivreRepository;
import biblio.repository.adherant.ExemplaireLivreRepository;
import biblio.repository.adherant.LivreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

@Service
public class LivreService {
    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private ExemplaireLivreRepository exemplaireLivreRepository;

    @Autowired
    private CategorieLivreAssociationRepository categorieLivreAssociationRepository;

    @Autowired
    private AuteurRepository auteurRepository;

    @Autowired
    private CategorieLivreRepository categorieLivreRepository;

    public List<biblio.entities.Auteur> getAllAuteurs() {
        return auteurRepository.findAll();
    }

    public List<biblio.entities.CategorieLivre> getAllCategories() {
        return categorieLivreRepository.findAll();
    }

    public List<Object[]> getFilteredLivreDetails(Integer auteurId, String langue, Date dateDebut, Date dateFin,
            Integer nbPagesMin, Integer nbPagesMax, Integer categorieId, Integer nbExMin, Integer nbExMax) {
        List<Livre> livres = livreRepository.findAll();
        List<Object[]> details = new ArrayList<>();
        for (Livre livre : livres) {
            if (auteurId != null && (livre.getAuteur() == null || !auteurId.equals(livre.getAuteur().getId_Auteur())))
                continue;
            if (langue != null && !langue.isEmpty()
                    && (livre.getLangue() == null || !livre.getLangue().equalsIgnoreCase(langue)))
                continue;
            if (dateDebut != null
                    && (livre.getDate_publication() == null || livre.getDate_publication().before(dateDebut)))
                continue;
            if (dateFin != null && (livre.getDate_publication() == null || livre.getDate_publication().after(dateFin)))
                continue;
            if (nbPagesMin != null && (livre.getNb_pages() == null || livre.getNb_pages() < nbPagesMin))
                continue;
            if (nbPagesMax != null && (livre.getNb_pages() == null || livre.getNb_pages() > nbPagesMax))
                continue;
            List<String> categories = getCategories(livre);
            if (categorieId != null) {
                boolean found = false;
                for (String cat : categories) {
                    biblio.entities.CategorieLivre catObj = categorieLivreRepository.findById(categorieId).orElse(null);
                    if (catObj != null && catObj.getNom().equals(cat)) {
                        found = true;
                        break;
                    }
                }
                if (!found)
                    continue;
            }
            long nbEx = getNombreExemplaires(livre);
            if (nbExMin != null && nbEx < nbExMin)
                continue;
            if (nbExMax != null && nbEx > nbExMax)
                continue;
            details.add(new Object[] { livre, categories, nbEx });
        }
        return details;
    }

    // Renvoie la liste enrichie pour la JSP (Livre, catégories, nb exemplaires)
    public List<Object[]> getAllLivreDetails() {
        List<Livre> livres = livreRepository.findAll();
        List<Object[]> details = new ArrayList<>();
        for (Livre livre : livres) {
            List<String> categories = getCategories(livre);
            long nbEx = getNombreExemplaires(livre);
            details.add(new Object[] { livre, categories, nbEx });
        }
        return details;
    }

    // ...existing code...

    public long getNombreExemplaires(Livre livre) {
        return exemplaireLivreRepository.countByLivre(livre);
    }

    public List<String> getCategories(Livre livre) {
        return categorieLivreAssociationRepository.findCategorieNomsByLivre(livre);
    }
}
