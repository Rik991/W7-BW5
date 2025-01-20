package it.epicode.EpicEnergyBE.provincia.comune;


import it.epicode.EpicEnergyBE.provincia.Provincia;
import it.epicode.EpicEnergyBE.provincia.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class ComuneService {

    @Autowired
    private ComuneRepository comuneRepository;

    @Autowired
    private ProvinciaRepository provinciaRepository;

    public void importComuniFromCSV(String csvFile) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                if (values.length >= 3) {
                    Comune comune = new Comune();
                    comune.setCodiceProvincia(values[0].trim());
                    comune.setProgressivoComune(values[1].trim());
                    comune.setDenominazione(values[2].trim());

                    // Cerca la provincia corrispondente
                    Provincia provincia = provinciaRepository
                            .findBySigla(values[0].trim())
                            .orElseThrow(() -> new RuntimeException("Provincia non trovata: " + values[0]));

                    comuneRepository.findByProvincia(provincia);

                    comune.setProvincia(provincia);
                    comuneRepository.save(comune);
                }
            }
        }
    }
}

