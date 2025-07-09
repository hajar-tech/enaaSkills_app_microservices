package com.enaaskills.briefservice.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity

public class Brief {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String title;
    private String description;
    private LocalDate dateLimit;



}
