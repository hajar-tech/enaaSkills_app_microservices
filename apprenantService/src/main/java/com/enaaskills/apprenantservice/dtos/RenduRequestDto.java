package com.enaaskills.apprenantservice.dtos;

import java.time.LocalDate;

public record RenduRequestDto (
    Long idBrief,
    Long idApprenant,
    String contenu,
    String description,
    LocalDate dateDepot
){
}
