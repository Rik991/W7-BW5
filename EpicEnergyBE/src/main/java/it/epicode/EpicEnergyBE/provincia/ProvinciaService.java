package it.epicode.EpicEnergyBE.provincia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class ProvinciaService {

    @Autowired
    private ProvinciaRepository provinciaRepository;

    public void importProvinceFromCSV(String csvFile) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                if (values.length >= 3) {
                    Provincia provincia = new Provincia();
                    provincia.setSigla(values[0].trim());
                    provincia.setNome(values[1].trim());
                    provincia.setRegione(values[2].trim());
                    provinciaRepository.save(provincia);
                }
            }
        }
    }

}
