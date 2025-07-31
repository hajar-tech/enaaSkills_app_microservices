package com.enaaskills.authservice.securityService;

import com.enaaskills.authservice.models.Admin;
import com.enaaskills.authservice.models.Utilisateur;
import com.enaaskills.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration

public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {

        String adminEmail = "admin@enaaskills.com";  // email admin par défaut

        if (!userRepository.existsByEmail(adminEmail)) {

            Admin admin = new Admin();  // instancie la sous-classe Admin
            admin.setNomComplet("admin");
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode("Admin123!"));
            // Ajoute autres champs si besoin

            userRepository.save(admin);
            System.out.println("Admin créé automatiquement.");
        } else {
            System.out.println("Admin déjà présent en base.");
        }
    }
}
