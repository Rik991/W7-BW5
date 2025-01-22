package it.epicode.EpicEnergyBE.fattura.stato_fattura;

import it.epicode.EpicEnergyBE.fattura.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StatoFatturaRepository extends JpaRepository<StatoFattura, Long> {
    Optional<StatoFattura> findByNome(String nome);
    void deleteByNome(String nome);
}