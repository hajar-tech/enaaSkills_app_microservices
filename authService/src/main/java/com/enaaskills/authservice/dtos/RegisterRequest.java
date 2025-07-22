package com.enaaskills.authservice.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Value;

public record RegisterRequest(
        String nom,

        String email,

        String password,


        String typeUtilisateur
){}





