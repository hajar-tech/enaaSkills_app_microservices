package com.enaaskills.validationservice.controller;

import com.enaaskills.validationservice.model.Validation;
import com.enaaskills.validationservice.repository.ValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/validations")
public class ValidationController {

    @Autowired
    private ValidationRepository validationRepository;

    @GetMapping
    public List<Validation> getAllValidations() {
        return validationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Validation> getValidationById(@PathVariable Long id) {
        return validationRepository.findById(id);
    }

    @PostMapping
    public Validation createValidation(@RequestBody Validation validation) {
        return validationRepository.save(validation);
    }

    @PutMapping("/{id}")
    public Validation updateValidation(@PathVariable Long id, @RequestBody Validation validationDetails) {
        Validation validation = validationRepository.findById(id).orElseThrow();
        validation.setStatut(validationDetails.getStatut());
        validation.setApprenantId(validationDetails.getApprenantId());
        validation.setCompetenceId(validationDetails.getCompetenceId());
        validation.setBriefId(validationDetails.getBriefId());
        validation.setRenduId(validationDetails.getRenduId());
        return validationRepository.save(validation);
    }

    @GetMapping("/apprenant/{apprenantId}")
    public List<Validation> getValidationsByApprenant(@PathVariable Long apprenantId) {
        return validationRepository.findByApprenantId(apprenantId);
    }

    @GetMapping("/brief/{briefId}")
    public List<Validation> getValidationsByBrief(@PathVariable Long briefId) {
        return validationRepository.findByBriefId(briefId);
    }

    @GetMapping("/competence/{competenceId}")
    public List<Validation> getValidationsByCompetence(@PathVariable Long competenceId) {
        return validationRepository.findByCompetenceId(competenceId);
    }
}
