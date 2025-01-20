package it.epicode.EpicEnergyBE.fattura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fatture")
public class FatturaController {
    @Autowired
    private FatturaService fatturaService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Fattura> createFattura(@RequestBody FatturaDTO fatturaDTO) {
        Fattura newFattura = fatturaService.createFattura(fatturaDTO);
        return ResponseEntity.ok(newFattura);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<List<Fattura>> getAllFatture() {
        List<Fattura> fatture = fatturaService.findAll();
        return ResponseEntity.ok(fatture);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Fattura> getFatturaById(@PathVariable Long id) {
        Fattura fattura = fatturaService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Fattura non trovata"));
        return ResponseEntity.ok(fattura);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Fattura> updateFattura(@PathVariable Long id, @RequestBody FatturaDTO fatturaDTO) {
        Fattura updatedFattura = fatturaService.updateFattura(id, fatturaDTO);
        return ResponseEntity.ok(updatedFattura);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteFattura(@PathVariable Long id) {
        fatturaService.deleteFattura(id);
        return ResponseEntity.noContent().build();
    }
}