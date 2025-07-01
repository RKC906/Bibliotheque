package biblio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Penalite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Penalite;
    private Date date_debut;
    private Date date_fin;
    
    @ManyToOne
    private Adherant adherant;

    public Penalite() {}

    // Getters and Setters
    public Integer getId_Penalite() {
        return id_Penalite;
    }
    public void setId_Penalite(Integer id_Penalite) {
        this.id_Penalite = id_Penalite;
    }
    public Date getDate_debut() {
        return date_debut;
    }
    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }
    public Date getDate_fin() {
        return date_fin;
    }
    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }
    public Adherant getAdherant() {
        return adherant;
    }
    public void setAdherant(Adherant adherant) {
        this.adherant = adherant;
    }
}