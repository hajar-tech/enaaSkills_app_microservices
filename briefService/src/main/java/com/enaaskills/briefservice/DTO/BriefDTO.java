package com.enaaskills.briefservice.DTO;

import java.time.LocalDate;

public record BriefDTO (
        String title,
        String description,
        LocalDate dateLimite
){




}
