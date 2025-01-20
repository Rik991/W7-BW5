package it.epicode.EpicEnergyBE.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class AuthRunner implements ApplicationRunner {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Creazione dell'utente admin se non esiste
        Optional<AppUser> adminUser = appUserService.findByUsername("admin");
        if (adminUser.isEmpty()) {
            RegisterRequest adminRequest = new RegisterRequest();
            adminRequest.setUsername("admin");
            adminRequest.setPassword(passwordEncoder.encode("adminpwd"));
            adminRequest.setEmail("admin@epicode.it");
            adminRequest.setNome("Admin");
            adminRequest.setCognome("User");
            appUserService.registerUser(adminRequest, null);
        }

        // Creazione dell'utente user se non esiste
        Optional<AppUser> normalUser = appUserService.findByUsername("user");
        if (normalUser.isEmpty()) {
            RegisterRequest userRequest = new RegisterRequest();
            userRequest.setUsername("user");
            userRequest.setPassword(passwordEncoder.encode("userpwd"));
            userRequest.setEmail("user@epicode.it");
            userRequest.setNome("User");
            userRequest.setCognome("Schiavo");
            appUserService.registerUser(userRequest, null);
        }
    }
}



