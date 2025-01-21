package it.epicode.EpicEnergyBE.fattura;

import it.epicode.EpicEnergyBE.cliente.Cliente;
import it.epicode.EpicEnergyBE.cliente.ClienteRepository;
import it.epicode.EpicEnergyBE.fattura.stato_fattura.StatoFattura;
import it.epicode.EpicEnergyBE.fattura.stato_fattura.StatoFatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public Fattura createFattura(FatturaDTO fatturaDTO) {
        Cliente cliente = clienteRepository.findById(fatturaDTO.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente non trovato"));

        StatoFattura statoFattura = new StatoFattura();
        statoFattura.setNome(fatturaDTO.getStatoFatturaNome());
        statoFattura = statoFatturaRepository.save(statoFattura);

        Fattura fattura = new Fattura();
        fattura.setData(fatturaDTO.getData());
        fattura.setImporto(fatturaDTO.getImporto());
        fattura.setNumero(fatturaDTO.getNumero());
        fattura.setCliente(cliente);
        fattura.setStatoFattura(statoFattura);

        return fatturaRepository.save(fattura);
    }

    public List<Fattura> findAll() {
        return fatturaRepository.findAll();
    }

    public Optional<Fattura> findById(Long id) {
        return fatturaRepository.findById(id);
    }

    @Transactional
    public Fattura updateFattura(Long id, FatturaDTO fatturaDTO) {
        Fattura fattura = fatturaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Fattura non trovata"));

        Cliente cliente = clienteRepository.findById(fatturaDTO.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente non trovato"));
        StatoFattura statoFattura = statoFatturaRepository.findById(fatturaDTO.getStatoFatturaId())
                .orElseThrow(() -> new IllegalArgumentException("Stato Fattura non trovato"));

        statoFattura.setNome(fatturaDTO.getStatoFatturaNome());
        statoFattura = statoFatturaRepository.save(statoFattura);

        fattura.setData(fatturaDTO.getData());
        fattura.setImporto(fatturaDTO.getImporto());
        fattura.setNumero(fatturaDTO.getNumero());
        fattura.setCliente(cliente);
        fattura.setStatoFattura(statoFattura);

        return fatturaRepository.save(fattura);
    }


    public void deleteFattura(Long id) {
        fatturaRepository.deleteById(id);
    }
}