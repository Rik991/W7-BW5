package it.epicode.EpicEnergyBE.provincia.comune;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ComuneController {

    @Autowired
    private ComuneService comuneService;


    public ResponseEntity<String> importaComuni(@RequestParam String filePath) {
        try {
            comuneService.importaComuni(filePath);
            return ResponseEntity.ok("Comuni importati con successo.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante l'importazione dei comuni.");
        }
    }
}


