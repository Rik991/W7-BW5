package it.epicode.EpicEnergyBE.provincia;


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
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;

    public ResponseEntity<String> importaProvince(@RequestParam String filePath) {
        try {
            provinciaService.importaProvince(filePath);
            return ResponseEntity.ok("Province importate con successo.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante l'importazione delle province.");
        }
    }

}
