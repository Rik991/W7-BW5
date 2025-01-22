package it.epicode.EpicEnergyBE.fattura.stato_fattura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stato_fattura")
public class StatoFatturaController {

    @Autowired
    private StatoFatturaService statoFatturaService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<StatoFattura> createStatoFattura(@RequestParam String nome){
        return new ResponseEntity<>(statoFatturaService.createStatoFattura(nome), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<StatoFattura>> getAllStatoFattura(){
        return ResponseEntity.ok(statoFatturaService.findAll());
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteStatoFattura(@RequestParam String nome){
        statoFatturaService.deleteStatoFattura(nome);
        return new ResponseEntity<>("Stato fattura eliminato correttamente!",HttpStatus.NO_CONTENT);
    }
}
