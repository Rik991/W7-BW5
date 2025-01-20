package it.epicode.EpicEnergyBE.cliente;


import it.epicode.EpicEnergyBE.indirizzo.Indirizzo;
import it.epicode.EpicEnergyBE.indirizzo.IndirizzoDTO;
import it.epicode.EpicEnergyBE.indirizzo.IndirizzoRepository;
import it.epicode.EpicEnergyBE.provincia.comune.Comune;
import it.epicode.EpicEnergyBE.provincia.comune.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private IndirizzoRepository indirizzoRepository;

    @Autowired
    private ComuneRepository comuneRepository;


    @Transactional
    public Cliente createCliente(ClienteDTO clienteDTO) {
        Indirizzo sedeLegale = convertToEntity(clienteDTO.getSedeLegale());
        Indirizzo sedeOperativa = convertToEntity(clienteDTO.getSedeOperativa());

        Cliente cliente = new Cliente();
        cliente.setRagioneSociale(clienteDTO.getRagioneSociale());
        cliente.setPartitaIva(clienteDTO.getPartitaIva());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setDataInserimento(clienteDTO.getDataInserimento());
        cliente.setDataUltimoContatto(clienteDTO.getDataUltimoContatto());
        cliente.setFatturatoAnnuale(clienteDTO.getFatturatoAnnuale());
        cliente.setPec(clienteDTO.getPec());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setEmailContatto(clienteDTO.getEmailContatto());
        cliente.setNomeContatto(clienteDTO.getNomeContatto());
        cliente.setCognomeContatto(clienteDTO.getCognomeContatto());
        cliente.setTelefonoContatto(clienteDTO.getTelefonoContatto());
        cliente.setLogoAziendale(clienteDTO.getLogoAziendale());
        cliente.setTipoCliente(clienteDTO.getTipoCliente());
        cliente.setSedeLegale(sedeLegale);
        cliente.setSedeOperativa(sedeOperativa);

        return clienteRepository.save(cliente);
    }

    private Indirizzo convertToEntity(IndirizzoDTO indirizzoDTO) {
        Comune comune = comuneRepository.findById(indirizzoDTO.getComuneId())
                .orElseThrow(() -> new IllegalArgumentException("Comune not found"));

        Indirizzo indirizzo = new Indirizzo();
        indirizzo.setVia(indirizzoDTO.getVia());
        indirizzo.setCivico(indirizzoDTO.getCivico());
        indirizzo.setLocalita(indirizzoDTO.getLocalita());
        indirizzo.setCap(indirizzoDTO.getCap());
        indirizzo.setComune(comune);

        return indirizzoRepository.save(indirizzo);
    }

    }
