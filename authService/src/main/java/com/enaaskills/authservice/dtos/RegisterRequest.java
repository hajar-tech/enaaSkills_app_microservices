package com.enaaskills.authservice.dtos;




public record RegisterRequest(
        String nom,

        String email,

        String password,


        String typeUtilisateur
){}





