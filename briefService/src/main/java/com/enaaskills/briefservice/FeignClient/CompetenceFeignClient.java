package com.enaaskills.briefservice.FeignClient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "competenceService")
public interface CompetenceFeignClient {

    @GetMapping("/competences/{id}")
   ResponseEntity<Map<String , Object>>  getById(@PathVariable("id") Long id);
}
