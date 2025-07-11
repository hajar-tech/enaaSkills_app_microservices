package com.enaaskills.competenceservice.models;


import com.enaaskills.competenceservice.enums.StatutCompetence;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Competence {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String titre;

    @Enumerated(EnumType.STRING)
    private StatutCompetence statut = StatutCompetence.EN_ATTENTE;

    @OneToMany(mappedBy = "competence", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SousCompetences> sousCompetences = new ArrayList<>();


    public StatutCompetence getStatut() {
        return statut;
    }

    public void setStatut(StatutCompetence statut) {
        this.statut = statut;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public List<SousCompetences> getSousCompetences() {
        return sousCompetences;
    }

    public void setSousCompetences(List<SousCompetences> sousCompetences) {
        this.sousCompetences = sousCompetences;
    }
}
