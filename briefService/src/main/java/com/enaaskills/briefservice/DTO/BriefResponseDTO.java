package com.enaaskills.briefservice.DTO;

import java.time.LocalDate;

public record BriefResponseDTO (
        Long id,
        String title,
        String description ,
        LocalDate dateLimite
){}
