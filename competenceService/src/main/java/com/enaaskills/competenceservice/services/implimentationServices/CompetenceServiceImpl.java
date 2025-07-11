package com.enaaskills.competenceservice.services.implimentationServices;



import com.enaaskills.competenceservice.dtos.CompetenceDto;
import com.enaaskills.competenceservice.dtos.SousCompetenceDto;
import com.enaaskills.competenceservice.enums.StatutCompetence;
import com.enaaskills.competenceservice.mappers.CompetenceMapper;
import com.enaaskills.competenceservice.models.Competence;
import com.enaaskills.competenceservice.models.SousCompetences;
import com.enaaskills.competenceservice.repositories.CompetenceRepository;
import com.enaaskills.competenceservice.repositories.SousCompetenceRepository;
import com.enaaskills.competenceservice.services.interfaceServices.CompetenceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompetenceServiceImpl implements CompetenceService {

    private final CompetenceRepository competenceRepository;
    private  final SousCompetenceRepository sousCompetenceRepository;
    private final CompetenceMapper competenceMapper;


    public CompetenceServiceImpl (CompetenceRepository competenceRepository , SousCompetenceRepository sousCompetenceRepository , CompetenceMapper competenceMapper){
        this.competenceRepository = competenceRepository;
        this.competenceMapper = competenceMapper;
        this.sousCompetenceRepository = sousCompetenceRepository;
    }

    @Override
    public CompetenceDto createCompetence(CompetenceDto dto) {
        Competence competence = competenceMapper.competenceDtoToCompetence(dto);
        competence.setId(null);//nouvelle entité
        if (competence.getSousCompetences() != null) {
            for (SousCompetences sc : competence.getSousCompetences()) {
                sc.setCompetence(competence);
            }
        }
        Competence saved = competenceRepository.save(competence);
        return competenceMapper.competenceToCompetenceDto(saved);
    }

    @Override
    public List<CompetenceDto> getAllCompetences() {
        List<Competence> competences = competenceRepository.findAll();
        return competences.stream()
                .map(competenceMapper::competenceToCompetenceDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompetenceDto updateCompetence(Long id, CompetenceDto dto) {
        Competence existing = competenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compétence non trouvée"));

        existing.setTitre(dto.titre());
        existing.setCode(dto.code());

        // mise à jour des sous-compétences existantes
        for (SousCompetenceDto scDto : dto.sousCompetences()) {
            SousCompetences sc = sousCompetenceRepository.findById(scDto.id())
                    .orElseThrow(() -> new RuntimeException("Sous-compétence non trouvée"));

            sc.setTitre(scDto.titre());
            sc.setStatut(StatutCompetence.valueOf(scDto.statut().toUpperCase()));
            sousCompetenceRepository.save(sc);
        }

        return competenceMapper.competenceToCompetenceDto(existing);
    }



//    public CompetenceDto updateCompetence(Long id, CompetenceDto dto) {
//        Competence existing = competenceRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Compétence non trouvée"));
//        existing.setTitre(dto.titre());
//        existing.setCode(dto.code());
//        // On ne modifie pas le statut ici : validation automatique
//        Competence updated = competenceRepository.save(existing);
//        return competenceMapper.competenceToCompetenceDto(updated);
//    }

    @Override
    public CompetenceDto getCompetenceById(Long id) {
        Competence c = competenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compétence non trouvée"));
        return competenceMapper.competenceToCompetenceDto(c);
    }

    @Override
    public void deleteCompetence(Long id) {

        competenceRepository.deleteById(id);

    }

    @Override
    public CompetenceDto validerAutomatiquementCompetence(Long id) {

        Competence competence = competenceRepository.findById(id).orElseThrow(()-> new RuntimeException("Compétence non trouvée"));

        List<SousCompetences> sousCompetences = competence.getSousCompetences();

        if(sousCompetences == null || sousCompetences.isEmpty()){
            competence.setStatut(StatutCompetence.EN_ATTENTE);
        }
        else {
            long isValide = sousCompetences.stream()
                    .filter(sc -> sc.getStatut().name().equalsIgnoreCase("VALIDE"))
                    .count();

            long nonValide = sousCompetences.stream()
                    .filter(sc -> sc.getStatut().name().equalsIgnoreCase("NON_VALIDE"))
                    .count();

            if (isValide > nonValide || isValide == nonValide){
                competence.setStatut(StatutCompetence.VALIDE);
            }else {
                competence.setStatut(StatutCompetence.NON_VALIDE);
            }
        }

        competenceRepository.save(competence);

        return competenceMapper.competenceToCompetenceDto(competence);
    }
}


