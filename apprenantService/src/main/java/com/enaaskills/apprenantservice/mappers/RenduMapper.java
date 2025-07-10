package com.enaaskills.apprenantservice.mappers;

import com.enaaskills.apprenantservice.dtos.RenduResponseDto;
import com.enaaskills.apprenantservice.models.Rendu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RenduMapper {
    @Mapping(source = "idApprenant", target = "apprenant.id")
    Rendu toEntity(RenduResponseDto renduDto);

    @Mapping(source = "apprenant.id", target = "idApprenant")
    RenduResponseDto toDto(Rendu rendu);


}
