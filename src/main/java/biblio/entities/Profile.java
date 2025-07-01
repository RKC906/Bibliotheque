package biblio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Profile;
    private String nom;

    public Profile() {}

    // Getters and Setters
    public Integer getId_Profile() {
        return id_Profile;
    }
    public void setId_Profile(Integer id_Profile) {
        this.id_Profile = id_Profile;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
}