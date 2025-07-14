package com.enaaskills.briefservice.Service;


import com.enaaskills.briefservice.DTO.BriefDTO;
import com.enaaskills.briefservice.DTO.CreateBriefRequestDTO;
import com.enaaskills.briefservice.FeignClient.CompetenceFeignClient;
import com.enaaskills.briefservice.Model.Brief;
import com.enaaskills.briefservice.Model.BriefCompetence;
import com.enaaskills.briefservice.Repository.BriefCompetenceRepository;
import com.enaaskills.briefservice.Repository.BriefRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BriefService {

    private final BriefRepository briefRepository;
    private final BriefCompetenceRepository briefCompetenceRepository;
    private final CompetenceFeignClient competenceFeignClient;

    public BriefService(BriefRepository briefRepository, BriefCompetenceRepository briefCompetenceRepository, CompetenceFeignClient competenceFeignClient) {
        this.briefRepository = briefRepository;
        this.briefCompetenceRepository = briefCompetenceRepository;
        this.competenceFeignClient = competenceFeignClient;
    }

    public Brief creatBrief(Brief brief) {
        return briefRepository.save(brief);
    }

    public List<Brief> getAllBriefs() {
        return briefRepository.findAll();
    }


    public BriefCompetence associeCompetence(Long briefId,Long competenceId)
    {
        Brief brief = briefRepository.findById(briefId).orElseThrow(()->new RuntimeException("Brief not trouvé"));

        BriefCompetence briefCompetence = new BriefCompetence();
        briefCompetence.setBrief(brief);
        briefCompetence.setCompetenceId(competenceId);

        return briefCompetenceRepository.save(briefCompetence);

    }

    public Brief createBriefDTO(Brief brief) {
        return briefRepository.save(brief);
    }

    public Brief getBrief(Long id) {
        return briefRepository.findById(id).orElseThrow(()->{
            return  new RuntimeException("Brief not found");
        });

    }



    public Brief createBriefWithCompetences(CreateBriefRequestDTO briefdto) {
        //creat brief
        Brief brief = new Brief();
        brief.setTitle(briefdto.title());
        brief.setDescription(briefdto.description());
        brief.setDateLimit(briefdto.dateLimit());
        Brief savedBrief = briefRepository.save(brief);

        //associer les competences

        for (Long competenceId : briefdto.competenceIds()) {
            try {
                var response = competenceFeignClient.getById(competenceId);
                if (response.getStatusCode().is2xxSuccessful()){
                    BriefCompetence bc = new BriefCompetence();
                    bc.setBrief(savedBrief);
                    bc.setCompetenceId(competenceId);
                    briefCompetenceRepository.save(bc);
                }else {
                    throw new RuntimeException("Compétence ID " + competenceId + " introuvable");
                }
            }catch (Exception e){
                throw new RuntimeException("Erreur lors de l’appel à Competence-Service : " + e.getMessage());
            }
        }
        return savedBrief;
    }

}
