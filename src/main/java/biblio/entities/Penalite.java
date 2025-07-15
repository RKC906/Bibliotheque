package biblio.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Penalite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPenalite;
    private Date dateDebut;
    private Date dateFin;

    @ManyToOne
    @JoinColumn(name = "adherant_idAdherant")
    private Adherant adherant;

    @ManyToOne
    @JoinColumn(name = "admin_id_Admin")
    private Admin admin;

    public Penalite() {}

    // Getters and Setters
    public Integer getIdPenalite() {
        return idPenalite;
    }

    public void setIdPenalite(Integer idPenalite) {
        this.idPenalite = idPenalite;
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
}
