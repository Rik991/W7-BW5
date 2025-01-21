package it.epicode.EpicEnergyBE.auth;

import it.epicode.EpicEnergyBE.cloudinary.CloudinaryService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.Set;

@Service
public class AppUserService {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public AppUser registerUser(RegisterRequest registerRequest, MultipartFile avatar, Set<Role> roles) {
        if (appUserRepository.existsByUsername(registerRequest.getUsername())) {
            throw new EntityExistsException("Username già in uso");
        }

        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        AppUser appUser = new AppUser();
        BeanUtils.copyProperties(registerRequest, appUser);
        if (avatar != null && !avatar.isEmpty()) {
            appUser.setAvatar(cloudinaryService.uploader(avatar,"usersT3").get("url").toString());
        }

        appUser.setRoles(roles);


        return appUserRepository.save(appUser);
    }

    public Optional<AppUser> findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    public AuthResponse authenticateUser(String username, String password)  {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            AppUser appUser = loadUserByUsername(username);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            AuthResponse authResponse = new AuthResponse();
            authResponse.setAccessToken(jwtTokenUtil.generateToken(userDetails) );

            authResponse.setUser(appUser);

            return authResponse;

        } catch (AuthenticationException e) {
            throw new SecurityException("Credenziali non valide", e);
        }
    }


    public AppUser loadUserByUsername(String username)  {
        AppUser appUser = appUserRepository.findByUsername(username)
            .orElseThrow(() -> new EntityNotFoundException("Utente non trovato con username: " + username));


        return appUser;
    }
}
