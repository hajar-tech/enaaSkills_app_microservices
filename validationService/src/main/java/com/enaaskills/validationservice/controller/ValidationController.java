package com.enaaskills.validationservice.controller;

import com.enaaskills.validationservice.model.Validation;
import com.enaaskills.validationservice.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/validations")
@CrossOrigin(origins = "*")
public class ValidationController {

    @Autowired
    private ValidationService validationService;

    @GetMapping
    public List<Validation> getAllValidations() {
        return validationService.getAllValidations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Validation> getValidationById(@PathVariable Long id) {
        Optional<Validation> validation = validationService.getValidationById(id);
        return validation.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Validation> createValidation(@RequestBody Validation validation) {
        try {
            Validation createdValidation = validationService.createValidation(validation);
            return ResponseEntity.ok(createdValidation);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Validation> updateValidation(@PathVariable Long id, @RequestBody Validation validationDetails) {
        try {
            Validation updatedValidation = validationService.updateValidation(id, validationDetails);
            return ResponseEntity.ok(updatedValidation);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteValidation(@PathVariable Long id) {
        validationService.deleteValidation(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/apprenant/{apprenantId}")
    public List<Validation> getValidationsByApprenant(@PathVariable Long apprenantId) {
        return validationService.getValidationsByApprenant(apprenantId);
    }

    @GetMapping("/brief/{briefId}")
    public List<Validation> getValidationsByBrief(@PathVariable Long briefId) {
        return validationService.getValidationsByBrief(briefId);
    }

    @GetMapping("/competence/{competenceId}")
    public List<Validation> getValidationsByCompetence(@PathVariable Long competenceId) {
        return validationService.getValidationsByCompetence(competenceId);
    }
}
