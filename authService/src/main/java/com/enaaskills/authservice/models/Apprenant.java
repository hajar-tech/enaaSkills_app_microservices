package com.enaaskills.authservice.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Apprenant")

public class Apprenant extends Utilisateur {
}
