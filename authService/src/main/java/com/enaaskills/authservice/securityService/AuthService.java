package com.enaaskills.authservice.securityService;


import com.enaaskills.authservice.configuration.JwtUtil;
import com.enaaskills.authservice.dtos.LoginRequest;
import com.enaaskills.authservice.dtos.LoginResponse;
import com.enaaskills.authservice.dtos.RegisterRequest;
import com.enaaskills.authservice.models.Apprenant;
import com.enaaskills.authservice.models.Formateur;
import com.enaaskills.authservice.models.Utilisateur;
import com.enaaskills.authservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder , JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public void registerUser(RegisterRequest registerRequest) {//throws for exeptions {
        if (userRepository.findByEmail(registerRequest.email()).isPresent()){
            throw new RuntimeException("email déjà utilisé !");

        }

        Utilisateur utilisateur;

        //créer user dynamiquement selon le typeUtilisateur
        switch (registerRequest.typeUtilisateur().toLowerCase()){
            case "apprenant" -> utilisateur = new Apprenant();
            case "formateur" -> utilisateur = new Formateur();
           // case "admin" -> utilisateur = new Admin();
            default -> throw new RuntimeException("Type d'utilisateur invalide");
        }

        utilisateur.setNomComplet(registerRequest.nom());
        utilisateur.setEmail(registerRequest.email());
        utilisateur.setPassword(passwordEncoder.encode(registerRequest.password()));

        userRepository.save(utilisateur);
    }



    public LoginResponse login(LoginRequest request){
        Utilisateur utilisateur = userRepository.findByEmail(request.email())
                .orElseThrow(()-> new RuntimeException("Email incorect !"));

        if (!passwordEncoder.matches(request.password(), utilisateur.getPassword())) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        String token = jwtUtil.generateToken(utilisateur);
        String role = utilisateur.getClass().getSimpleName().toUpperCase();

        return new LoginResponse(token, utilisateur.getId(),role , utilisateur.getEmail());


    }


    public Apprenant getApprenantById (Long id){
        return userRepository.findById(id)
                .filter(user -> user instanceof Apprenant)
                .map(user -> (Apprenant) user)
                .orElseThrow(()-> new RuntimeException("Apprenant introuvable avec id = \" + id"));
    }


}
