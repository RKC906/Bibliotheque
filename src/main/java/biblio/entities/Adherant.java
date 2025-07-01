package biblio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Adherant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Adherant;
    private String nom;
    private String prenom;
    private Date date_naissance;
    
    @ManyToOne
    private Authentification authentification;
    
    @ManyToOne
    private Profile profile;

    public Adherant() {}

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
}