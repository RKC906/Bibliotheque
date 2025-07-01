package biblio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CategorieLivreAssociation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    private Livre livre;
    
    @ManyToOne
    private CategorieLivre categorieLivre;

    public CategorieLivreAssociation() {}

    // Getters and Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Livre getLivre() {
        return livre;
    }
    public void setLivre(Livre livre) {
        this.livre = livre;
    }
    public CategorieLivre getCategorieLivre() {
        return categorieLivre;
    }
    public void setCategorieLivre(CategorieLivre categorieLivre) {
        this.categorieLivre = categorieLivre;
    }
}