package biblio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.Date;

import biblio.entities.Admin;

@Entity
public class Pret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPret;
    private Date dateDebut;
    private Date dateFin;

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
    public Integer getIdPret() {
        return idPret;
    }

    public void setIdPret(Integer idPret) {
        this.idPret = idPret;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
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