package com.enaaskills.briefservice.DTO;


import java.util.List;

public record BriefWithCompetencesDTO (
        Long id,
        String title,
        String description,
        List<CompetenceDto> Competences
) {
}
