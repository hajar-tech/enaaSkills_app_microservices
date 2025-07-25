package com.enaaskills.apprenantservice.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "authService" ,url = "http://localhost:8083")
public interface ApprenantClient {

    @GetMapping("/apprenants/{id}")
     ResponseEntity<ApprenantDto> getApprenantById(@PathVariable("id") Long id) ;


    }
