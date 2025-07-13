package com.enaaskills.apprenantservice.services;

import com.enaaskills.apprenantservice.dtos.CompetenceValidationDto;
import com.enaaskills.apprenantservice.dtos.RenduDetailDto;
import com.enaaskills.apprenantservice.dtos.RenduRequestDto;
import com.enaaskills.apprenantservice.feign.BriefFeignClient;
import com.enaaskills.apprenantservice.models.Apprenant;
import com.enaaskills.apprenantservice.models.Rendu;
import com.enaaskills.apprenantservice.repositories.ApprenantRepository;
import com.enaaskills.apprenantservice.repositories.RenduRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RenduService {

    private final BriefFeignClient briefFeignClient;
    private final ApprenantRepository apprenantRepository;
    private final RenduRepository renduRepository;

    public RenduService(BriefFeignClient briefFeignClient, ApprenantRepository apprenantRepository, RenduRepository renduRepository) {
        this.briefFeignClient = briefFeignClient;
        this.apprenantRepository = apprenantRepository;
        this.renduRepository = renduRepository;
    }

    public Rendu createRendu(RenduRequestDto dto){

        // 1. Vérifier que le Brief existe via Feign
        ResponseEntity<Map<String, Object>> response = briefFeignClient.getBriefsById(dto.idBrief());
        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new RuntimeException("Brief non trouvé");
        }

        // 2. Vérifier que l'apprenant existe
        Apprenant apprenant = apprenantRepository.findById(dto.idApprenant())
                .orElseThrow(() -> new RuntimeException("Apprenant introuvable"));



        // 3. Créer le rendu
        Rendu rendu = new Rendu();
        rendu.setContenu(dto.contenu());
        rendu.setDescription(dto.description());
        rendu.setDateDepot(dto.dateDepot());
        rendu.setIdBrief(dto.idBrief());
        rendu.setApprenant(apprenant);


        return renduRepository.save(rendu);

    }


    public Rendu getById (Long id){
        return renduRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Rendu non trouvé avec id " + id));
    }

    public RenduDetailDto getRenduDetail(Long id){
        Rendu rendu = renduRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rendu non trouvé"));

        Map<String, Object> briefData = briefFeignClient.getBriefsById(rendu.getIdBrief()).getBody();

        String titre = (String) briefData.get("titre");
//
//        List<Long> competenceIds = ((List<?>) briefData.get("competenceIds")).stream()
//                .map(idB -> ((Number) idB).longValue())
//                .toList();
//

//        List<CompetenceValidationDto> competences =
//                competenceService.getCompetencesWithValidation(competenceIds, rendu.getApprenant().getId());

        return new RenduDetailDto(
                rendu.getId(),
                rendu.getContenu(),
                rendu.getDateDepot(),
                titre
//                competences
        );
    }

}
