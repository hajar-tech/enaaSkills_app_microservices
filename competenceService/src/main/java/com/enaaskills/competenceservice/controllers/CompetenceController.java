package com.enaaskills.competenceservice.controllers;



import com.enaaskills.competenceservice.dtos.CompetenceDto;
import com.enaaskills.competenceservice.services.interfaceServices.CompetenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/competences")
public class CompetenceController {

    private  final CompetenceService competenceService;

    public CompetenceController (CompetenceService competenceService){
        this.competenceService = competenceService;
    }

    @PostMapping("/add")
    public ResponseEntity<CompetenceDto> create(@RequestBody CompetenceDto dto) {
        return ResponseEntity.ok(competenceService.createCompetence(dto));
    }

    @GetMapping
    public List<CompetenceDto> getAll() {
        return competenceService.getAllCompetences();
    }

    @GetMapping("/{id}")
    public CompetenceDto getById(@PathVariable Long id) {
        return competenceService.getCompetenceById(id);
    }

    @PutMapping("/{id}")
    public CompetenceDto update(@PathVariable Long id, @RequestBody CompetenceDto dto) {
        return competenceService.updateCompetence(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteCompetence(@PathVariable Long id){
        competenceService.deleteCompetence(id);
    }

    @PostMapping("/{id}/valider")
    public ResponseEntity<CompetenceDto> validerAutomatiqueCompetence (@PathVariable Long id){
        CompetenceDto dto = competenceService.validerAutomatiquementCompetence(id);
        return ResponseEntity.ok(dto);
    }
}
