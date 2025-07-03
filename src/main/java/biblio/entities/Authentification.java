package biblio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class Authentification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Authentification;
    @Column(nullable = false)
    private String mot_de_passe;
    @Column(nullable = false)
    private String email;

    public Authentification() {
    }

    // Getters and Setters
    public Integer getId_Authentification() {
        return id_Authentification;
    }

    public void setId_Authentification(Integer id_Authentification) {
        this.id_Authentification = id_Authentification;
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
}