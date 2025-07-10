package com.enaaskills.apprenantservice.controllers;

import com.enaaskills.apprenantservice.dtos.ApprenantDto;
import com.enaaskills.apprenantservice.models.Apprenant;
import com.enaaskills.apprenantservice.services.ApprenantService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apprenant")
public class ApprenantController {
    private final ApprenantService apprenantService;

    public ApprenantController (ApprenantService apprenantService){
        this.apprenantService = apprenantService;
    }

    @PostMapping("/create")
    public ApprenantDto createApprenant (@RequestBody ApprenantDto dto){
        return apprenantService.createApprenant(dto);
    }
}
