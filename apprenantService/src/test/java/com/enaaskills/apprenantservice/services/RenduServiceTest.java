package com.enaaskills.apprenantservice.services;

import com.enaaskills.apprenantservice.dtos.ApprenantDto;
import com.enaaskills.apprenantservice.dtos.RenduRequestDto;
import com.enaaskills.apprenantservice.feign.BriefFeignClient;
import com.enaaskills.apprenantservice.models.Rendu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RenduServiceTest {
    @Autowired
    RenduService renduService;

    @Autowired
    ApprenantService apprenantService;

    @Autowired
    BriefFeignClient briefFeignClient;


    @Test
    void createRendu() {
//        Long idBrief = 1L;
//        ApprenantDto apprenantDto = new ApprenantDto(
//                1L,
//                "hajar",
//                "zerhouni",
//                "hajar@gmail.com"
//        );
//
//        apprenantService.createApprenant(apprenantDto);
//
//
//        RenduRequestDto renduRequestDto = new RenduRequestDto(
//                idBrief,
//                apprenantDto.id(),
//                "contenu",
//                "description",
//                LocalDate.now()
//
//        );
//
//       Rendu result =  renduService.createRendu(renduRequestDto);
//
//       assertNotNull(result);


    }

    @Test
    void getById() {
    }
}