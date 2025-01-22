package it.epicode.EpicEnergyBE.fattura;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FatturaRepository extends JpaRepository<Fattura, Long> {
    Optional<Fattura> findByNumero(String numero);

    List<Fattura> findByClienteRagioneSociale(String ragioneSociale);
}
