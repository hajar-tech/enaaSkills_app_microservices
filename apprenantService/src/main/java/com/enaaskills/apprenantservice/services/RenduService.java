package com.enaaskills.apprenantservice.services;

import com.enaaskills.apprenantservice.dtos.RenduRequestDto;
import com.enaaskills.apprenantservice.feign.BriefFeignClient;
import com.enaaskills.apprenantservice.models.Apprenant;
import com.enaaskills.apprenantservice.models.Rendu;
import com.enaaskills.apprenantservice.repositories.ApprenantRepository;
import com.enaaskills.apprenantservice.repositories.RenduRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
}
