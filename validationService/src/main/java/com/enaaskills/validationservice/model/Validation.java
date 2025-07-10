package com.enaaskills.validationservice.model;

import jakarta.persistence.*;

@Entity
public class Validation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatutCompetence statut;
    private Long apprenantId;
    private Long competenceId;
    private Long briefId;
    private Long renduId;


    public Validation() {}

    public Validation(StatutCompetence statut, Long apprenantId, Long competenceId, Long briefId, Long renduId) {
        this.statut = statut;
        this.apprenantId = apprenantId;
        this.competenceId = competenceId;
        this.briefId = briefId;
        this.renduId = renduId;
    }

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public StatutCompetence getStatut() { return statut; }
    public void setStatut(StatutCompetence statut) { this.statut = statut; }

    public Long getApprenantId() { return apprenantId; }
    public void setApprenantId(Long apprenantId) { this.apprenantId = apprenantId; }

    public Long getCompetenceId() { return competenceId; }
    public void setCompetenceId(Long competenceId) { this.competenceId = competenceId; }

    public Long getBriefId() { return briefId; }
    public void setBriefId(Long briefId) { this.briefId = briefId; }

    public Long getRenduId() { return renduId; }
    public void setRenduId(Long renduId) { this.renduId = renduId; }
}