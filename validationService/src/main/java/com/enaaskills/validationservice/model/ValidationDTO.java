package com.enaaskills.validationservice.model;

public class ValidationDTO {
    private Long id;
    private String statut;
    private Long apprenantId;
    private Long competenceId;
    private Long briefId;
    private Long renduId;

    public ValidationDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public Long getApprenantId() { return apprenantId; }
    public void setApprenantId(Long apprenantId) { this.apprenantId = apprenantId; }

    public Long getCompetenceId() { return competenceId; }
    public void setCompetenceId(Long competenceId) { this.competenceId = competenceId; }

    public Long getBriefId() { return briefId; }
    public void setBriefId(Long briefId) { this.briefId = briefId; }

    public Long getRenduId() { return renduId; }
    public void setRenduId(Long renduId) { this.renduId = renduId; }
} 