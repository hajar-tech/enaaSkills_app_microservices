package com.enaaskills.validationservice.controller;

import com.enaaskills.validationservice.model.ValidationDTO;
import com.enaaskills.validationservice.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/validations")
public class ValidationController {

    @Autowired
    private ValidationService validationService;

    @GetMapping
    public List<ValidationDTO> getAllValidations() {
        return validationService.getAllValidations();
    }

    @GetMapping("/{id}")
    public Optional<ValidationDTO> getValidationById(@PathVariable Long id) {
        return validationService.getValidationById(id);
    }

    @PostMapping
    public ValidationDTO createValidation(@RequestBody ValidationDTO validationDTO) {
        return validationService.createValidation(validationDTO);
    }

    @PutMapping("/{id}")
    public ValidationDTO updateValidation(@PathVariable Long id, @RequestBody ValidationDTO validationDTO) {
        return validationService.updateValidation(id, validationDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteValidation(@PathVariable Long id) {
        validationService.deleteValidation(id);
    }

    @GetMapping("/apprenant/{apprenantId}")
    public List<ValidationDTO> getValidationsByApprenant(@PathVariable Long apprenantId) {
        return validationService.getValidationsByApprenant(apprenantId);
    }

    @GetMapping("/brief/{briefId}")
    public List<ValidationDTO> getValidationsByBrief(@PathVariable Long briefId) {
        return validationService.getValidationsByBrief(briefId);
    }

    @GetMapping("/competence/{competenceId}")
    public List<ValidationDTO> getValidationsByCompetence(@PathVariable Long competenceId) {
        return validationService.getValidationsByCompetence(competenceId);
    }
}
