package com.enaaskills.authservice.controllers;


import com.enaaskills.authservice.dtos.ApprenantDto;
import com.enaaskills.authservice.dtos.LoginRequest;
import com.enaaskills.authservice.dtos.LoginResponse;
import com.enaaskills.authservice.dtos.RegisterRequest;
import com.enaaskills.authservice.models.Apprenant;
import com.enaaskills.authservice.securityService.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register (@RequestBody RegisterRequest registerRequest) {

        try {
            authService.registerUser(registerRequest);
            return ResponseEntity.ok("Utilisateur enregistré avec succès.");
        }catch(Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body("Erreur lors de l'enregistrement : " + e.getMessage());
        }
    }



    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }


    @GetMapping("/apprenants/{id}")
    public ResponseEntity<ApprenantDto> getApprenantById(@PathVariable Long id) {
        try {
            Apprenant apprenant = authService.getApprenantById(id);
            return ResponseEntity.ok(new ApprenantDto(apprenant.getId(),apprenant.getNomComplet()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
