package biblio.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Admin 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Admin;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String motDePasse;

    // Relations
    @OneToMany(mappedBy = "admin")
    private List<Abonnement> abonnements;

    @OneToMany(mappedBy = "admin")
    private List<Penalite> penalites;

    @OneToMany(mappedBy = "admin")
    private List<Pret> prets;

    public Admin() {}

    public Integer getId_Admin() {
        return id_Admin;
    }
    public void setId_Admin(Integer id_Admin) {
        this.id_Admin = id_Admin;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMotDePasse() {
        return motDePasse;
    }
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    public List<Abonnement> getAbonnements() {
        return abonnements;
    }
    public void setAbonnements(List<Abonnement> abonnements) {
        this.abonnements = abonnements;
    }
    public List<Penalite> getPenalites() {
        return penalites;
    }
    public void setPenalites(List<Penalite> penalites) {
        this.penalites = penalites;
    }
    public List<Pret> getPrets() {
        return prets;
    }
    public void setPrets(List<Pret> prets) {
        this.prets = prets;
    }
}
