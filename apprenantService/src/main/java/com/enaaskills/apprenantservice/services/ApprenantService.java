package com.enaaskills.apprenantservice.services;

import com.enaaskills.apprenantservice.dtos.ApprenantDto;
import com.enaaskills.apprenantservice.mappers.ApprenantMapper;
import com.enaaskills.apprenantservice.models.Apprenant;
import com.enaaskills.apprenantservice.repositories.ApprenantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApprenantService {
    private final ApprenantRepository repo;
    private final ApprenantMapper mapper;


    public ApprenantService(ApprenantRepository repo, ApprenantMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public ApprenantDto createApprenant(ApprenantDto dto){
        Apprenant apprenant = mapper.toEntity(dto);
        apprenant.setNom(dto.nom());
        apprenant.setPrenom(dto.prenom());
        apprenant.setEmail(dto.email());
        return mapper.toDto(repo.save(apprenant));

    }

    public List<ApprenantDto> getAll() {
        return repo.findAll().stream()
                .map(apprenant -> new ApprenantDto(
                        apprenant.getId(),
                        apprenant.getNom(),
                        apprenant.getPrenom(),
                        apprenant.getEmail()
                ))
                .collect(Collectors.toList());
    }
}
