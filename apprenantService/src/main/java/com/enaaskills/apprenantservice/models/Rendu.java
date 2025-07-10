package com.enaaskills.apprenantservice.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Rendu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateDepot;
    private String contenu;

    private Long idBrief; // pour la liaison inter-service

    @ManyToOne
    @JoinColumn(name = "apprenant_id")
    private Apprenant apprenant;
}
