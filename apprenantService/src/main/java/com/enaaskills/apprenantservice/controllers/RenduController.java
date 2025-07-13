package com.enaaskills.apprenantservice.controllers;

import com.enaaskills.apprenantservice.dtos.RenduDetailDto;
import com.enaaskills.apprenantservice.dtos.RenduRequestDto;
import com.enaaskills.apprenantservice.models.Rendu;
import com.enaaskills.apprenantservice.services.RenduService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public  ResponseEntity<Rendu> getById (@PathVariable Long id){
        return ResponseEntity.ok(renduService.getById(id)) ;
    }

    @GetMapping("/renduDetail/{id}")
    public ResponseEntity<RenduDetailDto> getRenduDetail(@PathVariable Long id){
        RenduDetailDto renduDetail = renduService.getRenduDetail(id);
        return ResponseEntity.ok(renduDetail);
    }

}
