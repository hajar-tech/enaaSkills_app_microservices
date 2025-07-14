package com.enaaskills.briefservice.DTO;

import java.time.LocalDate;
import java.util.List;

public record BriefResponseDTO (
        Long id,
        String title,
        String description ,
        LocalDate dateLimite

){}
