package com.enaaskills.briefservice.Model;


import jakarta.persistence.*;

@Entity
public class BriefCompetence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToOne
    @JoinColumn(name = "brief_id")

    private Brief brief;

    private Long competenceId;
}
