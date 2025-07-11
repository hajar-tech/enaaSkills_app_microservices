package com.enaaskills.competenceservice.models;


import com.enaaskills.competenceservice.enums.StatutCompetence;
import jakarta.persistence.*;

@Entity
public class SousCompetences {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    @Enumerated(EnumType.STRING)
    private StatutCompetence statut = StatutCompetence.EN_ATTENTE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competence_id")
    private Competence competence;


    public StatutCompetence getStatut() {
        return statut;
    }

    public void setStatut(StatutCompetence statut) {
        this.statut = statut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }
}
