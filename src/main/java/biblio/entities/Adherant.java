package biblio.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import jakarta.persistence.OneToMany;

@Entity
public class Adherant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAdherant;
    private String nom;
    private String prenom;
    private Date dateNaissance;

    @ManyToOne
    @JoinColumn(name = "id_Status_Adherant")
    private StatusAdherant statusAdherant;

    @ManyToOne
    @JoinColumn(name = "id_Authentification")
    private Authentification authentification;

    @ManyToOne
    @JoinColumn(name = "id_Profile")
    private Profile profile;

    @OneToMany(mappedBy = "adherant")
    private List<Abonnement> abonnements;

    public Adherant() {
    }

    // Getters and Setters
    public Integer getIdAdherant() {
        return idAdherant;
    }

    public void setIdAdherant(Integer idAdherant) {
        this.idAdherant = idAdherant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public StatusAdherant getStatusAdherant() {
        return statusAdherant;
    }

    public void setStatusAdherant(StatusAdherant statusAdherant) {
        this.statusAdherant = statusAdherant;
    }

    public Authentification getAuthentification() {
        return authentification;
    }

    public void setAuthentification(Authentification authentification) {
        this.authentification = authentification;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Abonnement> getAbonnements() {
        return abonnements;
    }

    public void setAbonnements(List<Abonnement> abonnements) {
        this.abonnements = abonnements;
    }

    // Retourne la date de fin d'abonnement la plus récente
    public java.util.Date getDateFinAbonnement() {
        if (abonnements == null || abonnements.isEmpty())
            return null;
        return abonnements.stream()
                .map(Abonnement::getDateFinInscription)
                .filter(java.util.Objects::nonNull)
                .max(java.util.Date::compareTo)
                .orElse(null);
    }
}