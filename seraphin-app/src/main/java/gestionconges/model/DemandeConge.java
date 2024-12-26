package gestionconges.model;

import jakarta.persistence.*;


@Entity
public class DemandeConge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String typeConge;


    private String dateDebut;


    private String dateFin;


    private String motif;

    private String statut = "En attente";


    private String rejectionReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public DemandeConge() {}

    public DemandeConge(Long id, String typeConge, String dateDebut, String dateFin, String motif, String statut, String rejectionReason, User user) {
        this.id = id;
        this.typeConge = typeConge;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.motif = motif;
        this.statut = statut;
        this.rejectionReason = rejectionReason;
        this.user = user;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeConge() {
        return typeConge;
    }

    public void setTypeConge(String typeConge) {
        this.typeConge = typeConge;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getRejetReason() {
        return rejectionReason;
    }

    public void setRejetReason(String rejetReason) {
        this.rejectionReason = rejetReason;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "DemandeConge [id=" + id + ", typeConge=" + typeConge + ", dateDebut=" + dateDebut + ", dateFin="
                + dateFin + ", motif=" + motif + ", statut=" + statut + ", rejetReason=" + rejectionReason + ", user=" + user + "]";
    }
}
