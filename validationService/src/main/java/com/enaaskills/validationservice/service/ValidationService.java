package com.enaaskills.validationservice.service;

import com.enaaskills.validationservice.model.*;
import com.enaaskills.validationservice.repository.ValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ValidationService {
    @Autowired
    private ValidationRepository validationRepository;

    public List<ValidationDTO> getAllValidations() {
        return validationRepository.findAll().stream()
                .map(ValidationMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ValidationDTO> getValidationById(Long id) {
        return validationRepository.findById(id).map(ValidationMapper::toDTO);
    }

    public ValidationDTO createValidation(ValidationDTO dto) {
        Validation validation = ValidationMapper.toEntity(dto);
        Validation saved = validationRepository.save(validation);
        return ValidationMapper.toDTO(saved);
    }

    public ValidationDTO updateValidation(Long id, ValidationDTO dto) {
        Validation validation = validationRepository.findById(id).orElseThrow();
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
}
