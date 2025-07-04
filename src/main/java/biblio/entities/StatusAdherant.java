package biblio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class StatusAdherant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
