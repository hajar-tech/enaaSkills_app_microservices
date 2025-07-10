package com.enaaskills.briefservice.Mapper;

import com.enaaskills.briefservice.DTO.BriefDTO;
import com.enaaskills.briefservice.Model.Brief;

public class BriefMapper {

    public static Brief toEntity(BriefDTO briefDTO) {
        Brief brief = new Brief();
        brief.setTitle(briefDTO.titre());
        brief.setDescription(briefDTO.description());
        brief.setDateLimit(briefDTO.dateLimite());
        return brief;
    }

    public static BriefDTO toDTO(Brief brief) {
        return new BriefDTO(
                brief.getTitle(),
                brief.getDescription(),
                brief.getDateLimit()
        );

    }
}
