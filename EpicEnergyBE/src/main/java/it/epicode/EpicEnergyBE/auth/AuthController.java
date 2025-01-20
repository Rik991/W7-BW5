package it.epicode.EpicEnergyBE.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AppUserService appUserService;

    @PostMapping(path = "/register", consumes = {"multipart/form-data"})
    public ResponseEntity<AppUser> register(@RequestParam("appUser") String appUser,
                                            @RequestParam(value = "avatar", required = false) MultipartFile avatar){
        ObjectMapper objectMapper = new ObjectMapper();
        RegisterRequest registerRequest;

        try {
            registerRequest = objectMapper.readValue(appUser, RegisterRequest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(appUserService.registerUser(registerRequest, avatar, Set.of(Role.ROLE_USER)), HttpStatus.CREATED);


    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse authResponse = appUserService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(authResponse);
    }
}
