package com.enaaskills.authservice.models;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Formateur")

public class Formateur extends Utilisateur {
}
