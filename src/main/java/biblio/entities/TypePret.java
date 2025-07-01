package biblio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TypePret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_TypePret;
    private String nom;

    public TypePret() {}

    // Getters and Setters
    public Integer getId_TypePret() {
        return id_TypePret;
    }
    public void setId_TypePret(Integer id_TypePret) {
        this.id_TypePret = id_TypePret;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
}