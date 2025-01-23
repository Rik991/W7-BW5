package it.epicode.EpicEnergyBE.fattura;

import it.epicode.EpicEnergyBE.cliente.Cliente;
import it.epicode.EpicEnergyBE.cliente.ClienteRepository;
import it.epicode.EpicEnergyBE.fattura.stato_fattura.StatoFattura;
import it.epicode.EpicEnergyBE.fattura.stato_fattura.StatoFatturaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Validated
public class FatturaService {
    @Autowired
    private FatturaRepository fatturaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private StatoFatturaRepository statoFatturaRepository;

    public Fattura createFattura(String ragioneSociale,@Valid FatturaDTO fatturaDTO) {
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

    public Fattura updateFattura(String numero,@Valid FatturaDTO fatturaDTO) {
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

    public Page<Fattura> findByClienteRagioneSociale(String ragioneSociale, Pageable pageable) {
        return fatturaRepository.findFattureCliente(ragioneSociale, pageable);
    }

    public Page<Fattura> findByStatoFattura(String statoFatturaNome, Pageable pageable) {
        return fatturaRepository.findFattureByStatoFattura(statoFatturaNome, pageable);
    }

    public Page<Fattura> findByData(LocalDate dataInizio, LocalDate dataFine, Pageable pageable) {
        return fatturaRepository.findFattureByData(dataInizio, dataFine, pageable);
    }

    public Page<Fattura> findByAnno(int anno, Pageable pageable) {
        return fatturaRepository.findFattureByAnno(anno, pageable);
    }

    public Page<Fattura> findByImportoRange(Double minImporto, Double maxImporto, Pageable pageable) {
        return fatturaRepository.findFattureByImportoRange(minImporto, maxImporto, pageable);
    }
}