package com.enaaskills.validationservice.service;

import com.enaaskills.validationservice.model.Validation;
import com.enaaskills.validationservice.repository.ValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ValidationService {

    @Autowired
    private ValidationRepository validationRepository;
    
    @Autowired
    private RestTemplate restTemplate;

    // URLs for other services - will be injected from properties
    @Value("${apprenant.service.url}")
    private String apprenantServiceUrl;
    
    @Value("${brief.service.url}")
    private String briefServiceUrl;
    
    @Value("${competence.service.url}")
    private String competenceServiceUrl;

    public List<Validation> getAllValidations() {
        return validationRepository.findAll();
    }

    public Optional<Validation> getValidationById(Long id) {
        return validationRepository.findById(id);
    }

    public Validation createValidation(Validation validation) {
        // Check if apprenant exists
        if (!checkApprenantExists(validation.getApprenantId())) {
            throw new RuntimeException("Apprenant with ID " + validation.getApprenantId() + " not found");
        }
        
        // Check if brief exists
        if (!checkBriefExists(validation.getBriefId())) {
            throw new RuntimeException("Brief with ID " + validation.getBriefId() + " not found");
        }
        
        // Check if competence exists
        if (!checkCompetenceExists(validation.getCompetenceId())) {
            throw new RuntimeException("Competence with ID " + validation.getCompetenceId() + " not found");
        }
        
        // Check for duplicate validation
        if (validationRepository.existsByApprenantIdAndCompetenceIdAndBriefId(
                validation.getApprenantId(), 
                validation.getCompetenceId(), 
                validation.getBriefId())) {
            throw new RuntimeException("Validation already exists for this apprenant, competence, and brief combination");
        }
        
        return validationRepository.save(validation);
    }

    public Validation updateValidation(Long id, Validation validationDetails) {
        Validation validation = validationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Validation not found"));
        
        validation.setStatut(validationDetails.getStatut());
        validation.setApprenantId(validationDetails.getApprenantId());
        validation.setCompetenceId(validationDetails.getCompetenceId());
        validation.setBriefId(validationDetails.getBriefId());
        validation.setRenduId(validationDetails.getRenduId());
        
        return validationRepository.save(validation);
    }

    public void deleteValidation(Long id) {
        validationRepository.deleteById(id);
    }

    public List<Validation> getValidationsByApprenant(Long apprenantId) {
        return validationRepository.findByApprenantId(apprenantId);
    }

    public List<Validation> getValidationsByBrief(Long briefId) {
        return validationRepository.findByBriefId(briefId);
    }

    public List<Validation> getValidationsByCompetence(Long competenceId) {
        return validationRepository.findByCompetenceId(competenceId);
    }

    // Communication methods with other services
    private boolean checkApprenantExists(Long apprenantId) {
        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(
                apprenantServiceUrl + "/" + apprenantId, Object.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }

    private boolean checkBriefExists(Long briefId) {
        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(
                briefServiceUrl + "/" + briefId, Object.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }

    private boolean checkCompetenceExists(Long competenceId) {
        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(
                competenceServiceUrl + "/" + competenceId, Object.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }
}
