package biblio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Retour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Retour;
    private Date date_;
    
    @ManyToOne
    private Pret pret;

    public Retour() {}

    // Getters and Setters
    public Integer getId_Retour() {
        return id_Retour;
    }
    public void setId_Retour(Integer id_Retour) {
        this.id_Retour = id_Retour;
    }
    public Date getDate_() {
        return date_;
    }
    public void setDate_(Date date_) {
        this.date_ = date_;
    }
    public Pret getPret() {
        return pret;
    }
    public void setPret(Pret pret) {
        this.pret = pret;
    }
}