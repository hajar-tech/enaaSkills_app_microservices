package com.enaaskills.apprenantservice.controllers;

import com.enaaskills.apprenantservice.dtos.RenduRequestDto;
import com.enaaskills.apprenantservice.models.Rendu;
import com.enaaskills.apprenantservice.services.RenduService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rendus")
public class RenduController {

    private final RenduService renduService;

    public RenduController(RenduService renduService) {
        this.renduService = renduService;
    }

    @PostMapping
    public ResponseEntity<Rendu> createRendu(@RequestBody RenduRequestDto dto) {
        return ResponseEntity.ok(renduService.createRendu(dto));
    }
}
