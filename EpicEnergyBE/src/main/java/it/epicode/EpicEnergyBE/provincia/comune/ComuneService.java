package it.epicode.EpicEnergyBE.provincia.comune;


import it.epicode.EpicEnergyBE.provincia.Provincia;
import it.epicode.EpicEnergyBE.provincia.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ComuneService {

    @Autowired
    private ComuneRepository comuneRepository;

    @Autowired
    private ProvinciaRepository provinciaRepository;

    public void importaComuni(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        for (String line : lines) {
            String[] data = line.split(";");
            if (data.length == 4) {
                String codiceProvincia = data[0];
                String progressivo = data[1];
                String denominazione = data[2];
                String nomeProvincia = data[3];

                Optional<Provincia> provinciaOpt = provinciaRepository.findBySigla(nomeProvincia);
                if (provinciaOpt.isPresent()) {
                    Comune comune = new Comune();
                    comune.setCodiceProvincia(codiceProvincia);
                    comune.setProgressivo(progressivo);
                    comune.setDenominazione(denominazione);
                    comune.setProvincia(provinciaOpt.get());

                    comuneRepository.save(comune);
                }
            }
        }
    }
}

