package it.epicode.EpicEnergyBE.cliente;

import it.epicode.EpicEnergyBE.cloudinary.CloudinaryService;

import it.epicode.EpicEnergyBE.indirizzo.Indirizzo;
import it.epicode.EpicEnergyBE.indirizzo.IndirizzoRepository;

import it.epicode.EpicEnergyBE.provincia.comune.ComuneRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Validated
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private IndirizzoRepository indirizzoRepository;

    @Autowired
    private ComuneRepository comuneRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Transactional
    public Cliente createCliente(@Valid ClienteDTO clienteDTO, MultipartFile logo) {

        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(clienteDTO, cliente);

        if (logo != null && !logo.isEmpty()) {
            cliente.setLogoAziendale(cloudinaryService.uploader(logo, "loghiAziendeT3").get("url").toString());
        }

        Indirizzo sedeLegale = indirizzoRepository.save(clienteDTO.getSedeLegale());
        Indirizzo sedeOperativa = indirizzoRepository.save(clienteDTO.getSedeOperativa());

        cliente.setSedeLegale(sedeLegale);
        cliente.setSedeOperativa(sedeOperativa);


        return clienteRepository.save(cliente);
    }

    public Page<Cliente> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }


    public Cliente updateCliente(Long id, @Valid ClienteDTO clienteDTO, MultipartFile logo) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente non trovato"));

        BeanUtils.copyProperties(clienteDTO,cliente);

        if (logo != null && !logo.isEmpty()) {
            cliente.setLogoAziendale(cloudinaryService.uploader(logo, "loghiAziendeT3").get("url").toString());
        }

        Indirizzo sedeLegale = indirizzoRepository.save(clienteDTO.getSedeLegale());
        Indirizzo sedeOperativa = indirizzoRepository.save(clienteDTO.getSedeOperativa());

        cliente.setSedeLegale(sedeLegale);
        cliente.setSedeOperativa(sedeOperativa);

        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

//get filtrate

    public Page<Cliente> getClientiByfatturatoAnnualeRange(Double minImporto, Double maxImporto, Pageable pageable) {
        return clienteRepository.findByFatturatoAnnualeRange(minImporto, maxImporto, pageable);
    }

    public Page<Cliente> getClientiTraDateInserimento(LocalDate dataInizio, LocalDate dataFine, Pageable pageable) {
        return clienteRepository.findByDataInserimentoBetween(dataInizio, dataFine, pageable);
    }

    public Page<Cliente> getClientiTraDateUltimoContatto(LocalDate dataInizio, LocalDate dataFine, Pageable pageable) {
        return clienteRepository.findByDataUltimoContattoBetween(dataInizio, dataFine, pageable);
    }

    public Page<Cliente> getClientiByRagioneSociale(String ragioneSociale, Pageable pageable) {
        return clienteRepository.findByRagioneSocialeContains(ragioneSociale, pageable);
    }

}