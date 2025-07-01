package biblio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Reservation;
    private String date_reservation;
    private String status;
    
    @ManyToOne
    private Status statusEntity;
    
    @ManyToOne
    private ExemplaireLivre exemplaireLivre;

    public Reservation() {}

    // Getters and Setters
    public Integer getId_Reservation() {
        return id_Reservation;
    }
    public void setId_Reservation(Integer id_Reservation) {
        this.id_Reservation = id_Reservation;
    }
    public String getDate_reservation() {
        return date_reservation;
    }
    public void setDate_reservation(String date_reservation) {
        this.date_reservation = date_reservation;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Status getStatusEntity() {
        return statusEntity;
    }
    public void setStatusEntity(Status statusEntity) {
        this.statusEntity = statusEntity;
    }
    public ExemplaireLivre getExemplaireLivre() {
        return exemplaireLivre;
    }
    public void setExemplaireLivre(ExemplaireLivre exemplaireLivre) {
        this.exemplaireLivre = exemplaireLivre;
    }
}