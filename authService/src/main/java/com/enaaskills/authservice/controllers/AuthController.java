package com.enaaskills.authservice.controllers;


import com.enaaskills.authservice.configuration.JwtUtil;
import com.enaaskills.authservice.dtos.ApprenantDto;
import com.enaaskills.authservice.dtos.LoginRequest;
import com.enaaskills.authservice.dtos.LoginResponse;
import com.enaaskills.authservice.dtos.RegisterRequest;
import com.enaaskills.authservice.models.Apprenant;
import com.enaaskills.authservice.securityService.AuthService;
import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;
    @Autowired
    public AuthController(JwtUtil jwtUtil , AuthService authService) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register (@RequestBody  RegisterRequest registerRequest) {

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


    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestParam String token) {
        try {
            jwtUtil.extractAllClaims(token); // Si cette ligne passe sans exception, le token est OK
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }


    // Dans AuthController.java
    @GetMapping("/auth/extract-role")
    public ResponseEntity<String> extractRole(@RequestParam("token") String token) {
        Claims claims = jwtUtil.extractAllClaims(token);
        String role = claims.get("role", String.class);
        return ResponseEntity.ok(role);
    }

}
