package biblio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CategorieLivre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_CategorieLivre;
    private String nom;

    public CategorieLivre() {}

    // Getters and Setters
    public Integer getId_CategorieLivre() {
        return id_CategorieLivre;
    }
    public void setId_CategorieLivre(Integer id_CategorieLivre) {
        this.id_CategorieLivre = id_CategorieLivre;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
}