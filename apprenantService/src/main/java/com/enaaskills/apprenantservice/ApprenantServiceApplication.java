package com.enaaskills.apprenantservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.enaaskills.apprenantservice.feign")
public class ApprenantServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApprenantServiceApplication.class, args);
    }

}
