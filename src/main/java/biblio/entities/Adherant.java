package biblio.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import jakarta.persistence.OneToMany;

@Entity
public class Adherant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Adherant;
    private String nom;
    private String prenom;
    private Date date_naissance;

    @ManyToOne
    @JoinColumn(name = "id_Status_Adherant")
    private StatusAdherant statusAdherant;

    @ManyToOne
    @JoinColumn(name = "id_Authentification")
    private Authentification authentification;

    private String mot_de_passe;
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_Profile")
    private Profile profile;

    @OneToMany(mappedBy = "adherant")
    private List<Abonnement> abonnements;

    public Adherant() {
    }

    // Getters and Setters
    public Integer getId_Adherant() {
        return id_Adherant;
    }

    public void setId_Adherant(Integer id_Adherant) {
        this.id_Adherant = id_Adherant;
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

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
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

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
                .map(Abonnement::getDate_fin_inscription)
                .filter(java.util.Objects::nonNull)
                .max(java.util.Date::compareTo)
                .orElse(null);
    }
}