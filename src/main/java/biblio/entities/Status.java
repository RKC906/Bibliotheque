package biblio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Status;
    private String nom;

    public Status() {}

    // Getters and Setters
    public Integer getId_Status() {
        return id_Status;
    }
    public void setId_Status(Integer id_Status) {
        this.id_Status = id_Status;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
}