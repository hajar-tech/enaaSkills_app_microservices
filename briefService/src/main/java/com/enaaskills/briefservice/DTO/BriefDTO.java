package com.enaaskills.briefservice.DTO;

import java.time.LocalDate;
import java.util.List;

public record BriefDTO (
        String title,
        String description,
        LocalDate dateLimite
){




}
