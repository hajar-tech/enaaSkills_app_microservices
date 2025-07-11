package com.enaaskills.competenceservice.services.interfaceServices;


import com.enaaskills.competenceservice.dtos.CompetenceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompetenceService {

    CompetenceDto createCompetence (CompetenceDto dto);
    List<CompetenceDto> getAllCompetences();
    CompetenceDto updateCompetence(Long id, CompetenceDto dto);
    CompetenceDto getCompetenceById(Long id);
    void deleteCompetence(Long id);
    CompetenceDto validerAutomatiquementCompetence(Long id);

}
