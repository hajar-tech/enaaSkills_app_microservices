package com.enaaskills.apprenantservice.mappers;

import com.enaaskills.apprenantservice.dtos.RenduResponseDto;
import com.enaaskills.apprenantservice.models.Rendu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RenduMapper {
    @Mapping(source = "idApprenant", target = "idApprenant")
    Rendu toEntity(RenduResponseDto renduDto);

    @Mapping(source = "idApprenant", target = "idApprenant")
    RenduResponseDto toDto(Rendu rendu);


}
