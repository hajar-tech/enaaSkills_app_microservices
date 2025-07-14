package com.enaaskills.validationservice.service;

import com.enaaskills.validationservice.mapper.ValidationMapper;
import com.enaaskills.validationservice.model.*;
import com.enaaskills.validationservice.model.ValidationDTO;
import com.enaaskills.validationservice.repository.ValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ValidationService {
    @Autowired
    private ValidationRepository validationRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<ValidationDTO> getAllValidations() {
        return validationRepository.findAll().stream()
                .map(ValidationMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ValidationDTO> getValidationById(Long id) {
        return validationRepository.findById(id).map(ValidationMapper::toDTO);
    }

    public boolean briefExists(Long briefId) {
        String url = "http://localhost:8082/briefs/" + briefId; // adapte le port/service si besoin
        try {
            org.springframework.http.ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean competenceExists(Long competenceId) {
        String url = "http://localhost:8083/competences/" + competenceId; // adapte le port/service si besoin
        try {
            org.springframework.http.ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }

    public ValidationDTO createValidation(ValidationDTO dto) {
        if (validationRepository.existsByApprenantIdAndCompetenceIdAndBriefId(
                dto.getApprenantId(), dto.getCompetenceId(), dto.getBriefId())) {
            throw new RuntimeException("Validation already exists for this apprenant, competence, and brief.");
        }
        Validation validation = ValidationMapper.toEntity(dto);
        Validation saved = validationRepository.save(validation);
        return ValidationMapper.toDTO(saved);
    }

    public ValidationDTO updateValidation(Long id, ValidationDTO dto) {
        Validation validation = validationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Validation not found with id: " + id));
        validation.setStatut(dto.getStatut() != null ? StatutCompetence.valueOf(dto.getStatut()) : null);
        validation.setApprenantId(dto.getApprenantId());
        validation.setCompetenceId(dto.getCompetenceId());
        validation.setBriefId(dto.getBriefId());
        validation.setRenduId(dto.getRenduId());
        Validation saved = validationRepository.save(validation);
        return ValidationMapper.toDTO(saved);
    }

    public void deleteValidation(Long id) {
        validationRepository.deleteById(id);
    }

    public List<ValidationDTO> getValidationsByApprenant(Long apprenantId) {
        return validationRepository.findByApprenantId(apprenantId).stream()
                .map(ValidationMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ValidationDTO> getValidationsByBrief(Long briefId) {
        return validationRepository.findByBriefId(briefId).stream()
                .map(ValidationMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ValidationDTO> getValidationsByCompetence(Long competenceId) {
        return validationRepository.findByCompetenceId(competenceId).stream()
                .map(ValidationMapper::toDTO)
                .collect(Collectors.toList());
    }

    public boolean apprenantExists(Long apprenantId) {
        String url = "http://localhost:8081/apprenants/" + apprenantId; // adapte le port/service si besoin
        try {
            org.springframework.http.ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }
}
