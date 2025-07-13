package com.enaaskills.apprenantservice.services;

import com.enaaskills.apprenantservice.dtos.ApprenantDto;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ApprenantServiceTest {

    @Autowired
    ApprenantService apprenantService;

    @Test
    void createApprenant() {

        ApprenantDto apprenantDto = new ApprenantDto(
                1L,
                "salma",
                "salma",
                "salma@gmail.com"
        );


        ApprenantDto result = apprenantService.createApprenant(apprenantDto);


        assertNotNull(apprenantDto);
        assertEquals(result.email() , result.email());


    }

    @Test
    void getAll() {
        ApprenantDto apprenantDto = new ApprenantDto(
                1L,
                "salma",
                "salma",
                "salma@gmail.com"
        );
        ApprenantDto apprenantDto1 = new ApprenantDto(
                2L,
                "hajar",
                "hajar",
                "hajar@gmail.com"
        );

        apprenantService.createApprenant(apprenantDto);
        apprenantService.createApprenant(apprenantDto1);

        List<ApprenantDto> result = apprenantService.getAll();
        assertNotNull(result);
        assertNotEquals(3, result.size());
        assertEquals(2 , result.size());


    }

    @Test
    void getById() {
        ApprenantDto apprenantDto = new ApprenantDto(
                1L,
                "salma",
                "salma",
                "salma@gmail.com"
        );

        apprenantService.createApprenant(apprenantDto);

        ApprenantDto result = apprenantService.getById(1L);

        assertNotNull(result);
        assertEquals(apprenantDto.prenom() , apprenantDto.prenom());

    }
}