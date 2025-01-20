package it.epicode.EpicEnergyBE.provincia.comune;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/comuni")
public class ComuneController {

    @Autowired
    private ComuneService comuneService;

    @PostMapping
    public ResponseEntity<String> importComuni() {
        try {
            comuneService.importComuniFromCSV("src/main/resources/assets/comuni-italiani.csv");
            return ResponseEntity.ok("Importazione comuni completata con successo");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Errore durante l'importazione: " + e.getMessage());
        }
    }
}


