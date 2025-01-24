package it.epicode.EpicEnergyBE.cliente;

import com.github.javafaker.Faker;
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

    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (clienteRepository.findAll().isEmpty()) {
            Faker faker = new Faker();

            for (int i = 1; i <= 20; i++) {
                Comune comuneLegale = comuneRepository.findByDenominazione(faker.address().city());
                Comune comuneOperativo = comuneRepository.findByDenominazione(faker.address().city());

                Indirizzo indirizzoSedeLegale = new Indirizzo();
                indirizzoSedeLegale.setVia(faker.address().streetName());
                indirizzoSedeLegale.setCivico(String.valueOf(faker.number().numberBetween(1, 100)));
                indirizzoSedeLegale.setLocalita(faker.address().city());
                indirizzoSedeLegale.setCap(faker.address().zipCode());
                indirizzoSedeLegale.setComune(comuneLegale);

                Indirizzo indirizzoSedeOperativa = new Indirizzo();
                indirizzoSedeOperativa.setVia(faker.address().streetName());
                indirizzoSedeOperativa.setCivico(String.valueOf(faker.number().numberBetween(1, 100)));
                indirizzoSedeOperativa.setLocalita(faker.address().city());
                indirizzoSedeOperativa.setCap(faker.address().zipCode());
                indirizzoSedeOperativa.setComune(comuneOperativo);

                ClienteDTO clienteDTO = new ClienteDTO();

                clienteDTO.setRagioneSociale(faker.company().name());
                clienteDTO.setPartitaIva(faker.number().digits(11));
                clienteDTO.setEmail(faker.internet().emailAddress());
                clienteDTO.setDataInserimento(LocalDate.now());
                clienteDTO.setDataUltimoContatto(LocalDate.now().minusDays(faker.number().numberBetween(1, 365)));
                clienteDTO.setFatturatoAnnuale(faker.number().randomDouble(2, 50000, 200000));
                clienteDTO.setPec(faker.internet().emailAddress());
                clienteDTO.setTelefono(faker.phoneNumber().phoneNumber());
                clienteDTO.setEmailContatto(faker.internet().emailAddress());
                clienteDTO.setNomeContatto(faker.name().firstName());
                clienteDTO.setCognomeContatto(faker.name().lastName());
                clienteDTO.setTelefonoContatto(faker.phoneNumber().cellPhone());
                clienteDTO.setTipoCliente(i % 2 == 0 ? TipoCliente.SRL : TipoCliente.SPA);
                clienteDTO.setSedeLegale(indirizzoSedeLegale);
                clienteDTO.setSedeOperativa(indirizzoSedeOperativa);

                clienteService.createCliente(clienteDTO, null);
            }

        }
    }
}
