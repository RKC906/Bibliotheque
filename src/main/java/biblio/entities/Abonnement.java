package biblio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Abonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Abonnement;
    private Date date_inscription;
    private Date date_fin_inscription;
    
    @ManyToOne
    private Adherant adherant;

    public Abonnement() {}

    // Getters and Setters
    public Integer getId_Abonnement() {
        return id_Abonnement;
    }
    public void setId_Abonnement(Integer id_Abonnement) {
        this.id_Abonnement = id_Abonnement;
    }
    public Date getDate_inscription() {
        return date_inscription;
    }
    public void setDate_inscription(Date date_inscription) {
        this.date_inscription = date_inscription;
    }
    public Date getDate_fin_inscription() {
        return date_fin_inscription;
    }
    public void setDate_fin_inscription(Date date_fin_inscription) {
        this.date_fin_inscription = date_fin_inscription;
    }
    public Adherant getAdherant() {
        return adherant;
    }
    public void setAdherant(Adherant adherant) {
        this.adherant = adherant;
    }
}