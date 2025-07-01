package biblio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class Auteur 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Auteur;
    private String nom;
    private String prenom;
    private Date date_naissance;
    private String nationalite;
    private Date date_deces;
    private String biographie;
    private String photo;

    public Auteur() {}

    // Getters and Setters
    public Integer getId_Auteur() {
        return id_Auteur;
    }
    public void setId_Auteur(Integer id_Auteur) {
        this.id_Auteur = id_Auteur;
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
    public String getNationalite() {
        return nationalite;
    }
    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }
    public Date getDate_deces() {
        return date_deces;
    }
    public void setDate_deces(Date date_deces) {
        this.date_deces = date_deces;
    }
    public String getBiographie() {
        return biographie;
    }
    public void setBiographie(String biographie) {
        this.biographie = biographie;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
}