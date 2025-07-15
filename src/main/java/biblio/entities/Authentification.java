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
    private Integer idAuthentification;
    private String motDePasse;
    private String email;

    public Authentification() {
    }

    // Getters and Setters
    public Integer getIdAuthentification() {
        return idAuthentification;
    }

    public void setIdAuthentification(Integer idAuthentification) {
        this.idAuthentification = idAuthentification;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}