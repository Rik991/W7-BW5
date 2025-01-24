package it.epicode.EpicEnergyBE.fattura;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/fatture")
public class FatturaController {
    @Autowired
    private FatturaService fatturaService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Fattura> createFattura(@RequestParam String ragioneSociale,
                                                 @RequestBody FatturaDTO fatturaDTO) {
        return new ResponseEntity<>(fatturaService.createFattura(ragioneSociale, fatturaDTO), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Page<Fattura>> getAllFatture(Pageable pageable) {
        return ResponseEntity.ok(fatturaService.findAll(pageable));
    }

    @GetMapping("/{numero}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Fattura> getFattureByRagioneSociale(@PathVariable String numero) {
        Fattura fattura = fatturaService.findByNumero(numero)
                .orElseThrow(() -> new EntityNotFoundException("Fattura non trovata"));
        return ResponseEntity.ok(fattura);
    }

    @PutMapping("/{numero}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Fattura> updateFattura(@PathVariable String numero, @RequestBody FatturaDTO fatturaDTO) {
        Fattura updatedFattura = fatturaService.updateFattura(numero, fatturaDTO);
        return ResponseEntity.ok(updatedFattura);
    }


    @GetMapping("/ragione_sociale")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Page<Fattura>> getFattureByClienteRagioneSociale(@RequestParam String ragioneSociale, Pageable pageable) {
        return ResponseEntity.ok(fatturaService.findByClienteRagioneSociale(ragioneSociale, pageable));
    }

    @GetMapping("/stato_fattura")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Page<Fattura>> getFattureByStatoFattura(@RequestParam String statoFatturaNome, Pageable pageable) {
        return ResponseEntity.ok(fatturaService.findByStatoFattura(statoFatturaNome, pageable));
    }

    @GetMapping("/data")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Page<Fattura>> getFattureByData(@RequestParam LocalDate dataInizio, @RequestParam LocalDate dataFine, Pageable pageable) {
        return ResponseEntity.ok(fatturaService.findByData(dataInizio, dataFine, pageable));
    }

    @GetMapping("/anno")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Page<Fattura>> getFattureByAnno(@RequestParam int anno, Pageable pageable) {
        return ResponseEntity.ok(fatturaService.findByAnno(anno, pageable));
    }

    @GetMapping("/importo_range")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Page<Fattura>> getFattureByImportoRange(@RequestParam Double minImporto, @RequestParam Double maxImporto, Pageable pageable) {
        return ResponseEntity.ok(fatturaService.findByImportoRange(minImporto, maxImporto, pageable));
    }

    @GetMapping("/ultimo-numero")
    public ResponseEntity<Integer> getUltimoNumeroFattura() {
        Integer ultimoNumero = fatturaService.getUltimoNumeroFattura();
        return ResponseEntity.ok(ultimoNumero);
    }
}