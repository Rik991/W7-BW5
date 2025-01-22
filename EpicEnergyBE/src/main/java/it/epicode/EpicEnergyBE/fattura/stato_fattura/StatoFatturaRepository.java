package it.epicode.EpicEnergyBE.fattura.stato_fattura;

import it.epicode.EpicEnergyBE.fattura.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatoFatturaRepository extends JpaRepository<StatoFattura, Long> {
    List<Fattura> findByClienteRagioneSociale(String ragioneSociale);
}