package biblio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.Date;

import biblio.entities.Admin;

@Entity
public class Pret 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pret;
    private Date date_debut;
    private Date date_fin;

    @ManyToOne
    private Adherant adherant;

    @ManyToOne
    private Admin admin;

    @ManyToOne
    private ExemplaireLivre exemplaireLivre;

    @ManyToOne
    private TypePret typePret;
    
  

    public Pret() {
    }

    // Getters and Setters
    public Integer getId_pret() {
        return id_pret;
    }

    public void setId_pret(Integer id_pret) {
        this.id_pret = id_pret;
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

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public ExemplaireLivre getExemplaireLivre() {
        return exemplaireLivre;
    }

    public void setExemplaireLivre(ExemplaireLivre exemplaireLivre) {
        this.exemplaireLivre = exemplaireLivre;
    }

      public TypePret getTypePret() {
        return typePret;
    }

    public void setTypePret(TypePret typePret) {
        this.typePret = typePret;
    }
}