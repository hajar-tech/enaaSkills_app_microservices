package com.enaaskills.competenceservice.dtos;



import java.util.List;

public record CompetenceDto(
        Long id,
        String code,
        String titre,
        String statut,
        List<SousCompetenceDto> sousCompetences
) {
}
