package com.enaaskills.apprenantservice.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Apprenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;

    @OneToMany(mappedBy = "apprenant", cascade = CascadeType.ALL)
    private List<Rendu> rendus = new ArrayList<>();
}
