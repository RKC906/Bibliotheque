package biblio.entities;

import jakarta.persistence.*;

@Entity
public class ProfileCategorieLivreAssociation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Profile profile;

    @ManyToOne
    private CategorieLivre categorieLivre;

    public ProfileCategorieLivreAssociation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public CategorieLivre getCategorieLivre() {
        return categorieLivre;
    }

    public void setCategorieLivre(CategorieLivre categorieLivre) {
        this.categorieLivre = categorieLivre;
    }
}
