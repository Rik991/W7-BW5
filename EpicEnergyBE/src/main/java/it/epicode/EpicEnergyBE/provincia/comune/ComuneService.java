package it.epicode.EpicEnergyBE.provincia.comune;


import it.epicode.EpicEnergyBE.provincia.Provincia;
import it.epicode.EpicEnergyBE.provincia.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@Transactional
public class ComuneService {

    @Autowired
    private ComuneRepository comuneRepository;

    @Autowired
    private ProvinciaRepository provinciaRepository;

    public void importaComuni(String filePath) throws IOException {
        Logger logger = Logger.getLogger(ComuneService.class.getName());
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        int lineNumber = 0;

        for (String line : lines) {
            lineNumber++;
            String[] data = line.split(";");

            if (data.length != 4) {
                logger.warning("Invalid number of columns at line " + lineNumber + ": " + line);
                continue;
            }

            String codiceProvincia = data[0].trim();
            String progressivo = data[1].trim();
            String denominazione = data[2].trim();
            String nomeProvincia = data[3].trim();

            if (codiceProvincia.isEmpty() || progressivo.isEmpty() || denominazione.isEmpty() || nomeProvincia.isEmpty()) {
                logger.warning("Missing values at line " + lineNumber + ": " + line);
                continue;
            }

            try {
                Optional<Provincia> provincia = provinciaRepository.findBySigla(nomeProvincia);

                if (provincia.isEmpty()) {
                    provincia = provinciaRepository.findByNome(nomeProvincia);
                }

                if (provincia.isPresent()) {
                    Comune comune = new Comune();
                    comune.setCodiceProvincia(codiceProvincia);

                    comune.setProgressivo(progressivo);
                    comune.setDenominazione(denominazione);
                    comune.setProvincia(provincia.get());

                    comuneRepository.save(comune);
                } else {
                    logger.warning("Provincia not found for sigla/nome: " + nomeProvincia + " at line " + lineNumber);
                }
            } catch (Exception e) {
                logger.severe("Error processing line " + lineNumber + ": " + e.getMessage());
            }
        }
    }

    public List<Comune> findAll() {
        return comuneRepository.findAll();
    }
}


