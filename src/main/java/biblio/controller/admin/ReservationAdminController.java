package biblio.controller.admin;

import biblio.entities.Reservation;
import biblio.entities.ExemplaireLivre;
import biblio.entities.Livre;
import biblio.entities.Adherant;
import biblio.repository.adherant.ReservationRepository;
import biblio.repository.adherant.AdherantRepository;
import biblio.repository.admin.PenaliteRepository;
import biblio.repository.adherant.ReglePretRepository;
import biblio.repository.adherant.ExemplaireLivreRepository;
import biblio.repository.adherant.LivreRepository;
import biblio.entities.Penalite;
import biblio.entities.ReglePret;
import biblio.entities.Profile;
import biblio.entities.Status;
import biblio.entities.StatusAdherant;
import biblio.entities.TypePret;
import biblio.repository.admin.PretRepository;
import biblio.entities.Pret;
import biblio.repository.adherant.CategorieLivreAssociationRepository;
import biblio.repository.adherant.ProfileCategorieLivreAssociationRepository;
import biblio.entities.CategorieLivre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/admin/reservations")
public class ReservationAdminController {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private AdherantRepository adherantRepository;
    @Autowired
    private PenaliteRepository penaliteRepository;
    @Autowired
    private ReglePretRepository reglePretRepository;
    @Autowired
    private ExemplaireLivreRepository exemplaireLivreRepository;
    @Autowired
    private LivreRepository livreRepository;
    @Autowired
    private PretRepository pretRepository;
    @Autowired
    private CategorieLivreAssociationRepository categorieLivreAssociationRepository;
    @Autowired
    private ProfileCategorieLivreAssociationRepository profileCategorieLivreAssociationRepository;
    @Value("${pret.duree.jours:15}")
    private int dureePretJours;

    @GetMapping("")
    public String listeReservations(Model model) {
        List<Reservation> reservations = reservationRepository.findAll();
        model.addAttribute("reservations", reservations);
        // Pour chaque réservation, on récupère l'adhérant via l'exemplaire
        for (Reservation reservation : reservations) {
            ExemplaireLivre ex = reservation.getExemplaireLivre();
            Adherant adherant = null;
            if (ex != null && ex.getId_ExemplaireLivre() != null) {
                adherant = adherantRepository.findAdherantByReservationId(reservation.getIdReservation());
            }
            model.addAttribute("adherant_" + reservation.getIdReservation(), adherant);
        }
        return "admin/Reservations";
    }

    @PostMapping("/accepter")
    public String accepterReservation(@RequestParam("reservationId") Integer reservationId,
            RedirectAttributes redirectAttributes) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation == null) {
            redirectAttributes.addFlashAttribute("error", "Réservation introuvable.");
            return "redirect:/admin/reservations";
        }
        Adherant adherant = adherantRepository.findAdherantByReservationId(reservationId);
        if (adherant == null) {
            redirectAttributes.addFlashAttribute("error", "Aucun adhérant trouvé pour cette réservation.");
            return "redirect:/admin/reservations";
        }
        // 1. Vérifier si l'utilisateur est pénalisé
        boolean penalise = penaliteRepository.existsByAdherantAndDateFinAfter(adherant, new Date());
        if (penalise) {
            redirectAttributes.addFlashAttribute("error", "L'utilisateur est pénalisé.");
            return "redirect:/admin/reservations";
        }
        // 2. Vérifier le nombre de livres déjà empruntés (prêts actifs)
        ReglePret regle = reglePretRepository.findByProfile(adherant.getProfile());
        int maxLivres = regle != null ? regle.getNbrPretLivre() : 0;
        long nbPrets = pretRepository.countByAdherantAndDateFinAfter(adherant, new Date());
        if (nbPrets >= maxLivres) {
            redirectAttributes.addFlashAttribute("error",
                    "L'utilisateur a atteint le nombre maximal de livres empruntés.");
            return "redirect:/admin/reservations";
        }
        // 3. Vérifier le statut de l'adhérant
        if (adherant.getStatusAdherant() == null
                || !Objects.equals(adherant.getStatusAdherant().getIdStatusAdherant(), 1)) {
            redirectAttributes.addFlashAttribute("error", "Le statut de l'adhérant n'est pas actif.");
            return "redirect:/admin/reservations";
        }
        // 4. Vérifier la validité de l'abonnement
        Date dateFinAbonnement = adherant.getDateFinAbonnement();
        if (dateFinAbonnement == null || dateFinAbonnement.before(new Date())) {
            redirectAttributes.addFlashAttribute("error", "L'abonnement de l'adhérant n'est plus valide.");
            return "redirect:/admin/reservations";
        }
        // 5. Vérifier la disponibilité de l'exemplaire
        ExemplaireLivre exemplaire = reservation.getExemplaireLivre();
        if (exemplaire == null || exemplaireLivreRepository.findById(exemplaire.getId_ExemplaireLivre()).isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Exemplaire non disponible.");
            return "redirect:/admin/reservations";
        }
        // 6. Vérifier le profil de l'utilisateur par rapport au livre (compatibilité
        // profil/catégorie)
        Livre livre = exemplaire != null ? exemplaire.getLivre() : null;
        if (livre != null) {
            // Récupérer les catégories du livre
            java.util.List<String> categoriesLivre = categorieLivreAssociationRepository
                    .findCategorieNomsByLivre(livre);
            // Récupérer les catégories autorisées pour le profil de l'adhérant
            java.util.List<CategorieLivre> categoriesProfil = profileCategorieLivreAssociationRepository
                    .findCategoriesByProfile(adherant.getProfile());
            java.util.Set<String> nomsCategoriesProfil = new java.util.HashSet<>();
            for (CategorieLivre cat : categoriesProfil) {
                nomsCategoriesProfil.add(cat.getNom());
            }
            boolean compatible = false;
            for (String catLivre : categoriesLivre) {
                if (nomsCategoriesProfil.contains(catLivre)) {
                    compatible = true;
                    break;
                }
            }
            if (!compatible) {
                redirectAttributes.addFlashAttribute("error",
                        "Le profil de l'adhérant n'est pas compatible avec la catégorie du livre.");
                return "redirect:/admin/reservations";
            }
        }
        // Si tout est OK, passer le statut à "Accepter" et créer un prêt
        Status statusAccepte = new Status();
        statusAccepte.setId_Status(2); // 2 = Accepter
        reservation.setStatusEntity(statusAccepte);
        reservationRepository.save(reservation);
        // Création du prêt
        Pret pret = new Pret();
        pret.setAdherant(adherant);
        pret.setAdmin(null); // À renseigner si admin connecté
        pret.setExemplaireLivre(exemplaire);
        pret.setTypePret(reservation.getTypePret());
        pret.setDateDebut(new Date());
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(java.util.Calendar.DAY_OF_MONTH, dureePretJours);
        pret.setDateFin(cal.getTime());
        pretRepository.save(pret);
        redirectAttributes.addFlashAttribute("success", "Réservation acceptée.");
        return "redirect:/admin/reservations";
    }

    @PostMapping("/refuser")
    public String refuserReservation(@RequestParam("reservationId") Integer reservationId,
            RedirectAttributes redirectAttributes) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation == null) {
            redirectAttributes.addFlashAttribute("error", "Réservation introuvable.");
            return "redirect:/admin/reservations";
        }
        Status statusRefuse = new Status();
        statusRefuse.setId_Status(3); // 3 = Refuser
        reservation.setStatusEntity(statusRefuse);
        reservationRepository.save(reservation);
        redirectAttributes.addFlashAttribute("success", "Réservation refusée.");
        return "redirect:/admin/reservations";
    }
}
