package com.enaaskills.apprenantservice.dtos;

import java.time.LocalDate;


public record RenduDetailDto (
        Long id,
        String contenu,
        LocalDate deteDepot,
        String briefTitre
        //List<Long> competencesIds,
){

}
