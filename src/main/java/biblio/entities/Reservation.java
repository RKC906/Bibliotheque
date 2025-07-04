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
    private Integer idReservation;
    private String dateReservation;
    private String status;

    @ManyToOne
    private TypePret typePret;

    @ManyToOne
    private Status statusEntity;

    @ManyToOne
    private ExemplaireLivre exemplaireLivre;

    public Reservation() {
    }

    // Getters and Setters
    public Integer getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Integer idReservation) {
        this.idReservation = idReservation;
    }

    public String getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(String dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TypePret getTypePret() {
        return typePret;
    }

    public void setTypePret(TypePret typePret) {
        this.typePret = typePret;
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