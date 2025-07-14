package com.enaaskills.briefservice.DTO;

import java.time.LocalDate;
import java.util.List;

public record CreateBriefRequestDTO(

        String title,
        String description,
        LocalDate dateLimit,
        List<Long> competenceIds

) {
}
