package biblio.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import biblio.entities.Admin;

@Entity
public class Abonnement {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAbonnement") // Correspond au nom de colonne dans la table
    private Integer idAbonnement; // Nom de la propriété Java
    
    @Column(name = "dateInscription")
    private Date dateInscription;
    
    @Column(name = "dateFinInscription")
    private Date dateFinInscription;
    
    @Column(name = "admin_id_Admin")
    private Admin admin;
    
    @ManyToOne
    @JoinColumn(name = "adherant_idAdherant") // Correspond au nom de colonne FK dans la table
    private Adherant adherant;

    public Abonnement() {
    }

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