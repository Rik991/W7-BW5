package it.epicode.EpicEnergyBE.cliente;

import it.epicode.EpicEnergyBE.indirizzo.Indirizzo;
import it.epicode.EpicEnergyBE.provincia.comune.Comune;
import it.epicode.EpicEnergyBE.provincia.comune.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class ClienteRunner implements ApplicationRunner {

    @Autowired
    private ComuneRepository comuneRepository;

    @Autowired
    private ClienteService clienteService;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (clienteService.findAll().isEmpty()) {
            Comune comuneLegale = comuneRepository.findByDenominazione("Marsala");
            Comune comuneOperativo = comuneRepository.findByDenominazione("Catania");

            Indirizzo indirizzoSedeLegale = new Indirizzo();
            indirizzoSedeLegale.setVia("via Mazzini");
            indirizzoSedeLegale.setCivico("26");
            indirizzoSedeLegale.setLocalita("Marsala");
            indirizzoSedeLegale.setCap("91025");
            indirizzoSedeLegale.setComune(comuneLegale);

            Indirizzo indirizzoSedeOperativa = new Indirizzo();
            indirizzoSedeOperativa.setVia("Via Roma");
            indirizzoSedeOperativa.setCivico("50");
            indirizzoSedeOperativa.setLocalita("Catania");
            indirizzoSedeOperativa.setCap("95124");
            indirizzoSedeOperativa.setComune(comuneOperativo);

            ClienteDTO clienteDTO = new ClienteDTO();

            clienteDTO.setRagioneSociale("Epicode SRL");
            clienteDTO.setPartitaIva("A34456");
            clienteDTO.setEmail("epicode@epicode.it");
            clienteDTO.setDataInserimento(LocalDate.now());
            clienteDTO.setDataUltimoContatto(LocalDate.of(2025, 01, 20));
            clienteDTO.setFatturatoAnnuale(54200.0);
            clienteDTO.setPec("epicode@epicode.it");
            clienteDTO.setTelefono("444221");
            clienteDTO.setEmailContatto("danilofumuso@gmail.com");
            clienteDTO.setNomeContatto("Danilo");
            clienteDTO.setCognomeContatto("Fumuso");
            clienteDTO.setTelefonoContatto("4483291");
            clienteDTO.setTipoCliente(TipoCliente.SRL);
            clienteDTO.setSedeLegale(indirizzoSedeLegale);
            clienteDTO.setSedeOperativa(indirizzoSedeOperativa);

            clienteService.createCliente(clienteDTO, null);
        }
    }
}
