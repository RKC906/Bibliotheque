package biblio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class StatusAdherant {
    @Id
    private Integer idStatusAdherant;
    private String nom;

    public StatusAdherant() {
    }

    public Integer getIdStatusAdherant() {
        return idStatusAdherant;
    }

    public void setIdStatusAdherant(Integer idStatusAdherant) {
        this.idStatusAdherant = idStatusAdherant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
