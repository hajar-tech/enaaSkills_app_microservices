package com.enaaskills.apprenantservice.mappers;

import com.enaaskills.apprenantservice.dtos.RenduDto;
import com.enaaskills.apprenantservice.models.Rendu;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RenduMapper {
    RenduDto toDto(Rendu rendu);
    Rendu toEntity(RenduDto renduDto);

}
