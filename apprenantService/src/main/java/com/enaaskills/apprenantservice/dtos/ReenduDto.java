package com.enaaskills.apprenantservice.dtos;

import java.time.LocalDate;

public record ReenduDto(
        Long id,
        LocalDate dateDepot,
        String contenu,
        Long apprenantId,
        Long idBrief
) {
}
