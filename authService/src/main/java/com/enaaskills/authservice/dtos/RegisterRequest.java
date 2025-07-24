package com.enaaskills.authservice.dtos;

import jakarta.persistence.Enumerated;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        String nom,
        String email,
        String password,
        String typeUtilisateur
) {
}
