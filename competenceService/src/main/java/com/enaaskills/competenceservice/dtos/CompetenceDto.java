package com.enaaskills.competenceservice.dtos;



import java.security.SecureRandom;
import java.util.List;
import java.util.SimpleTimeZone;

public record CompetenceDto(
        Long id,
        String code,
        String titre,
        String statut,
        List<SousCompetenceDto> sousCompetences
) {
}
