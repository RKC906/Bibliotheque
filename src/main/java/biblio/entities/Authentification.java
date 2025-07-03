package biblio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Authentification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Authentification;
    private String motDePasse;
    private String email;

    public Authentification() {}

    // Getters and Setters
    public Integer getId_Authentification() {
        return id_Authentification;
    }
    public void setId_Authentification(Integer id_Authentification) {
        this.id_Authentification = id_Authentification;
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