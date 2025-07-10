package com.enaaskills.apprenantservice.mappers;

import com.enaaskills.apprenantservice.dtos.ApprenantDto;
import com.enaaskills.apprenantservice.models.Apprenant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApprenantMapper {
    ApprenantDto toDto (Apprenant apprenant);
    Apprenant toEntity (ApprenantDto apprenentDto);
}
