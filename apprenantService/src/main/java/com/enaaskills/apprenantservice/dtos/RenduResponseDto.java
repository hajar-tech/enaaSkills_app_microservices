package com.enaaskills.apprenantservice.dtos;

import java.time.LocalDate;

public record RenduResponseDto(
        Long id,
        Long idBrief,
        String contenu,
        LocalDate dateDepot,
        Long idApprenant
) {
}
