package com.enaaskills.competenceservice.mappers;


import com.enaaskills.competenceservice.dtos.CompetenceDto;
import com.enaaskills.competenceservice.dtos.SousCompetenceDto;
import com.enaaskills.competenceservice.models.Competence;
import com.enaaskills.competenceservice.models.SousCompetences;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompetenceMapper {

    CompetenceMapper INSTANCE = Mappers.getMapper(CompetenceMapper.class);
    CompetenceDto competenceToCompetenceDto(Competence competence);
    Competence competenceDtoToCompetence(CompetenceDto dto);


    SousCompetenceDto sousCompetenceToSousCompetenceDto(SousCompetences sousCompetence);
    SousCompetences sousCompetenceDtoToSousCompetence(SousCompetenceDto dto);

    List<SousCompetenceDto> toSousCompetenceDtos(List<SousCompetences> list);
    List<SousCompetences> toSousCompetenceEntities(List<SousCompetenceDto> list);


}
