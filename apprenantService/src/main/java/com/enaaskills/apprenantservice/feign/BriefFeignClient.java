package com.enaaskills.apprenantservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



import java.util.Map;


@FeignClient(name = "briefService" ,url = "http://localhost:8082")
public interface BriefFeignClient {


    @GetMapping("/Briefs/{id}")
    ResponseEntity<Map<String, Object>> getBriefsById(@PathVariable("id") Long id);


}
