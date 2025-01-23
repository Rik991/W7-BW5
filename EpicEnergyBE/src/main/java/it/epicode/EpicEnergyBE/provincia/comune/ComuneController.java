package it.epicode.EpicEnergyBE.provincia.comune;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/comuni")
public class ComuneController {

    @Autowired
    private ComuneService comuneService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<List<Comune>> getAllComuni() {
        return ResponseEntity.ok(comuneService.findAll());
    }


}


