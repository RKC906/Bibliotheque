package biblio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class StatusAdherant {
    @Id
    private Integer id_Status_Adherant;
    private String nom;

    public StatusAdherant() {
    }

    public Integer getId_Status_Adherant() {
        return id_Status_Adherant;
    }

    public void setId_Status_Adherant(Integer id_Status_Adherant) {
        this.id_Status_Adherant = id_Status_Adherant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
