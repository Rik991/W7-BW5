package it.epicode.EpicEnergyBE.fattura;

import it.epicode.EpicEnergyBE.cliente.Cliente;
import it.epicode.EpicEnergyBE.cliente.ClienteRepository;
import it.epicode.EpicEnergyBE.fattura.stato_fattura.StatoFattura;
import it.epicode.EpicEnergyBE.fattura.stato_fattura.StatoFatturaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FatturaService {
    @Autowired
    private FatturaRepository fatturaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private StatoFatturaRepository statoFatturaRepository;

    @Transactional
    public Fattura createFattura(String ragioneSociale,FatturaDTO fatturaDTO) {
        Cliente cliente = clienteRepository.findByRagioneSociale(ragioneSociale)
                .orElseThrow(() -> new EntityNotFoundException("Cliente non trovato"));

        StatoFattura statoFattura = statoFatturaRepository.findByNome(fatturaDTO.getStatoFatturaNome())
                .orElseThrow(() -> new EntityNotFoundException("Stato fattura non trovato"));

        Fattura fattura = new Fattura();
        fattura.setData(fatturaDTO.getData());
        fattura.setImporto(fatturaDTO.getImporto());
        fattura.setNumero(fatturaDTO.getNumero());
        fattura.setCliente(cliente);
        fattura.setStatoFattura(statoFattura);

        return fatturaRepository.save(fattura);
    }

    public Page<Fattura> findAll(Pageable pageable) {
        return fatturaRepository.findAll(pageable);
    }

    public Optional<Fattura> findByNumero(String numero) {
        return fatturaRepository.findByNumero(numero);
    }

    @Transactional
    public Fattura updateFattura(String numero, FatturaDTO fatturaDTO) {
        Fattura fattura = fatturaRepository.findByNumero(numero)
                .orElseThrow(() -> new EntityNotFoundException("Fattura non trovata"));

        Cliente cliente = clienteRepository.findById(fatturaDTO.getClienteId())
                .orElseThrow(()-> new EntityNotFoundException("Cliente non trovato!"));

        StatoFattura statoFattura= statoFatturaRepository.findByNome(fatturaDTO.getStatoFatturaNome())
                .orElseThrow(()-> new EntityNotFoundException("StatoFattura non trovato!"));

        fattura.setData(fatturaDTO.getData());
        fattura.setImporto(fatturaDTO.getImporto());
        fattura.setNumero(fatturaDTO.getNumero());
        fattura.setCliente(cliente);
        fattura.setStatoFattura(statoFattura);

        return fatturaRepository.save(fattura);
    }

    public void deleteFattura(String numero) {
        fatturaRepository.deleteByNumero(numero);
    }
}