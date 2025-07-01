package biblio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ExemplaireLivre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_ExemplaireLivre;
    
    @ManyToOne
    private Livre livre;

    public ExemplaireLivre() {}

    // Getters and Setters
    public Integer getId_ExemplaireLivre() {
        return id_ExemplaireLivre;
    }
    public void setId_ExemplaireLivre(Integer id_ExemplaireLivre) {
        this.id_ExemplaireLivre = id_ExemplaireLivre;
    }
    public Livre getLivre() {
        return livre;
    }
    public void setLivre(Livre livre) {
        this.livre = livre;
    }
}