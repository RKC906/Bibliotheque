package biblio.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Abonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAbonnement;

    @Column(name = "dateInscription")
    private Date dateInscription;

    @Column(name = "dateFinInscription")
    private Date dateFinInscription;

    @ManyToOne
    @JoinColumn(name = "admin_id_Admin")
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "adherant_idAdherant")
    private Adherant adherant;

    public Abonnement() {}

    // Getters and Setters
    public Integer getIdAbonnement() {
        return idAbonnement;
    }

    public void setIdAbonnement(Integer idAbonnement) {
        this.idAbonnement = idAbonnement;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public Date getDateFinInscription() {
        return dateFinInscription;
    }

    public void setDateFinInscription(Date dateFinInscription) {
        this.dateFinInscription = dateFinInscription;
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
