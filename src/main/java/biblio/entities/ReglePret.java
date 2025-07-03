package biblio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.sql.Time;

@Entity
public class ReglePret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_ReglePret;
    private Integer nbrPretLivre;
    private Time tempsPretLivre;
    
    @ManyToOne
    private Profile profile;

    public ReglePret() {}

    // Getters and Setters
    public Integer getId_ReglePret() {
        return id_ReglePret;
    }
    public void setId_ReglePret(Integer id_ReglePret) {
        this.id_ReglePret = id_ReglePret;
    }
    public Integer getNbrPretLivre() {
        return nbrPretLivre;
    }
    public void setNbrPretLivre(Integer nbrPretLivre) {
        this.nbrPretLivre = nbrPretLivre;
    }
    public Time getTempsPretLivre() {
        return tempsPretLivre;
    }
    public void setTempsPretLivre(Time tempsPretLivre) {
        this.tempsPretLivre = tempsPretLivre;
    }
    public Profile getProfile() {
        return profile;
    }
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}