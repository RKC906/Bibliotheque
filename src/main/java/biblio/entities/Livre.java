package biblio.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Livre;
    private String titre;
    private Date date_publication;
    private Integer nb_pages;
    private String langue;
    private String tags;
    
    @ManyToOne
    private Auteur auteur;

    public Livre() {}

    // Getters and Setters
    public Integer getId_Livre() {
        return id_Livre;
    }
    public void setId_Livre(Integer id_Livre) {
        this.id_Livre = id_Livre;
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public Date getDate_publication() {
        return date_publication;
    }
    public void setDate_publication(Date date_publication) {
        this.date_publication = date_publication;
    }
    public Integer getNb_pages() {
        return nb_pages;
    }
    public void setNb_pages(Integer nb_pages) {
        this.nb_pages = nb_pages;
    }
    public String getLangue() {
        return langue;
    }
    public void setLangue(String langue) {
        this.langue = langue;
    }
    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
    public Auteur getAuteur() {
        return auteur;
    }
    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }
}