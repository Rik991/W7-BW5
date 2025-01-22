package it.epicode.EpicEnergyBE.cliente;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.epicode.EpicEnergyBE.indirizzo.Indirizzo;
import it.epicode.EpicEnergyBE.provincia.comune.Comune;
import it.epicode.EpicEnergyBE.provincia.comune.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/clienti-completi")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ComuneRepository comuneRepository;

    @PostMapping(consumes = {"multipart/form-data"})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Cliente> createCliente(@RequestParam("ragioneSociale") String ragioneSociale,
                                                 @RequestParam("partitaIva") String partitaIva,
                                                 @RequestParam("email") String email,
                                                 @RequestParam(value = "dataUltimoContatto", required = false) LocalDate dataUltimoContatto,
                                                 @RequestParam("fatturatoAnnuale") Double fatturatoAnnuale,
                                                 @RequestParam("pec") String pec,
                                                 @RequestParam("telefono") String telefono,
                                                 @RequestParam(value = "emailContatto", required = false) String emailContatto,
                                                 @RequestParam(value = "nomeContatto", required = false) String nomeContatto,
                                                 @RequestParam(value = "cognomeContatto", required = false) String cognomeContatto,
                                                 @RequestParam(value = "telefonoContatto", required = false) String telefonoContatto,
                                                 @RequestParam("tipoCliente") TipoCliente tipoCliente,
                                                 @RequestParam("viaSedeLegale") String viaSedeLegale,
                                                 @RequestParam("civicoSedeLegale") String civicoSedeLegale,
                                                 @RequestParam("localitaSedeLegale") String localitaSedeLegale,
                                                 @RequestParam("capSedeLegale") String capSedeLegale,
                                                 @RequestParam("comuneSedeLegale") String comuneSedeLegale,
                                                 @RequestParam("viaSedeOperativa") String viaSedeOperativa,
                                                 @RequestParam("civicoSedeOperativa") String civicoSedeOperativa,
                                                 @RequestParam("localitaSedeOperativa") String localitaSedeOperativa,
                                                 @RequestParam("capSedeOperativa") String capSedeOperativa,
                                                 @RequestParam("comuneSedeOperativa") String comuneSedeOperativa,
                                                 @RequestParam(value = "logoAziendale", required = false) MultipartFile logo) throws JsonProcessingException {


        String comuneSedeLegaleCapitalized = StringUtils.capitalize(comuneSedeLegale);
        String comuneSedeOperativaCapitalized = StringUtils.capitalize(comuneSedeOperativa);

        Comune comuneLegale = comuneRepository.findByDenominazione(comuneSedeLegaleCapitalized);
        Comune comuneOperativo = comuneRepository.findByDenominazione(comuneSedeOperativaCapitalized);

        Indirizzo indirizzoSedeLegale = new Indirizzo();
        indirizzoSedeLegale.setVia(viaSedeLegale);
        indirizzoSedeLegale.setCivico(civicoSedeLegale);
        indirizzoSedeLegale.setLocalita(localitaSedeLegale);
        indirizzoSedeLegale.setCap(capSedeLegale);
        indirizzoSedeLegale.setComune(comuneLegale);

        Indirizzo indirizzoSedeOperativa = new Indirizzo();
        indirizzoSedeOperativa.setVia(viaSedeOperativa);
        indirizzoSedeOperativa.setCivico(civicoSedeOperativa);
        indirizzoSedeOperativa.setLocalita(localitaSedeOperativa);
        indirizzoSedeOperativa.setCap(capSedeOperativa);
        indirizzoSedeOperativa.setComune(comuneOperativo);

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setRagioneSociale(ragioneSociale);
        clienteDTO.setPartitaIva(partitaIva);
        clienteDTO.setEmail(email);
        clienteDTO.setDataInserimento(LocalDate.now());
        clienteDTO.setDataUltimoContatto(dataUltimoContatto);
        clienteDTO.setFatturatoAnnuale(fatturatoAnnuale);
        clienteDTO.setPec(pec);
        clienteDTO.setTelefono(telefono);
        clienteDTO.setEmailContatto(emailContatto);
        clienteDTO.setNomeContatto(nomeContatto);
        clienteDTO.setCognomeContatto(cognomeContatto);
        clienteDTO.setTelefonoContatto(telefonoContatto);
        clienteDTO.setTipoCliente(tipoCliente);
        clienteDTO.setSedeLegale(indirizzoSedeLegale);
        clienteDTO.setSedeOperativa(indirizzoSedeOperativa);

        return new ResponseEntity<>(clienteService.createCliente(clienteDTO, logo), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Page<Cliente>> getAllClienti(Pageable pageable) {

        return ResponseEntity.ok(clienteService.findAll(pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Cliente cliente = clienteService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente non trovato"));
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        Cliente updatedCliente = clienteService.updateCliente(id, clienteDTO);
        return ResponseEntity.ok(updatedCliente);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

}