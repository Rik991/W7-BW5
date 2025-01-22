package it.epicode.EpicEnergyBE.fattura.stato_fattura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class StatoFatturaRunner implements ApplicationRunner {

    @Autowired
    private StatoFatturaService statoFatturaService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (statoFatturaService.findAll().isEmpty()) {

            statoFatturaService.createStatoFattura("non pagata");
            statoFatturaService.createStatoFattura("parzialmente pagata");
            statoFatturaService.createStatoFattura("pagata");
            statoFatturaService.createStatoFattura("scaduta");
            statoFatturaService.createStatoFattura("rifiutata");
            statoFatturaService.createStatoFattura("in sollecito");
            statoFatturaService.createStatoFattura("annullata");
            statoFatturaService.createStatoFattura("rimborsata");
            statoFatturaService.createStatoFattura("archiviata");
            statoFatturaService.createStatoFattura("bozza");

        }
    }
}
