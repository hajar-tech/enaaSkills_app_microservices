package com.enaaskills.validationservice.mapper;

import com.enaaskills.validationservice.dto.ValidationDTO;
import com.enaaskills.validationservice.model.StatutCompetence;
import com.enaaskills.validationservice.model.Validation;

public class ValidationMapper {
    public static ValidationDTO toDTO(Validation validation) {
        ValidationDTO dto = new ValidationDTO();
        dto.setId(validation.getId());
        dto.setStatut(validation.getStatut() != null ? validation.getStatut().name() : null);
        dto.setApprenantId(validation.getApprenantId());
        dto.setCompetenceId(validation.getCompetenceId());
        dto.setBriefId(validation.getBriefId());
        dto.setRenduId(validation.getRenduId());
        return dto;
    }

    public static Validation toEntity(ValidationDTO dto) {
        Validation validation = new Validation();
        validation.setId(dto.getId());
        validation.setStatut(dto.getStatut() != null ? StatutCompetence.valueOf(dto.getStatut()) : null);
        validation.setApprenantId(dto.getApprenantId());
        validation.setCompetenceId(dto.getCompetenceId());
        validation.setBriefId(dto.getBriefId());
        validation.setRenduId(dto.getRenduId());
        return validation;
    }
} 