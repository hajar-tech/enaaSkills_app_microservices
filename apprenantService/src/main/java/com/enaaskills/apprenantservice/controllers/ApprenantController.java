package com.enaaskills.apprenantservice.controllers;

import com.enaaskills.apprenantservice.dtos.ApprenantDto;
import com.enaaskills.apprenantservice.models.Apprenant;
import com.enaaskills.apprenantservice.services.ApprenantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apprenants")
public class ApprenantController {
    private final ApprenantService apprenantService;

    public ApprenantController (ApprenantService apprenantService){
        this.apprenantService = apprenantService;
    }

    @PostMapping("/create")
    public ApprenantDto createApprenant (@RequestBody ApprenantDto dto){
        return apprenantService.createApprenant(dto);
    }

    @GetMapping
    public List<ApprenantDto> list() {
        return apprenantService.getAll();
    }

    @GetMapping("/{id}")
    public  ApprenantDto getById(@PathVariable Long id){
       return apprenantService.getById(id);
    }
}
