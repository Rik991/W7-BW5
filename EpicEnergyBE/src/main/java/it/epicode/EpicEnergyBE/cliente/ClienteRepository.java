package it.epicode.EpicEnergyBE.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByRagioneSociale(String ragioneSociale);

    @Query("SELECT c FROM Cliente c WHERE c.fatturatoAnnuale BETWEEN 0 AND 20000.00")
    Page<Cliente> findByFatturatoAnnualeBetween0And20000(Pageable pageable);

    @Query("SELECT c FROM Cliente c WHERE c.fatturatoAnnuale BETWEEN 20000.01 AND 50000.00")
    Page<Cliente> findByFatturatoAnnualeBetween20000And50000(Pageable pageable);

    @Query("SELECT c FROM Cliente c WHERE c.fatturatoAnnuale BETWEEN 50000.01 AND 100000.00")
    Page<Cliente> findByFatturatoAnnualeBetween50000And100000(Pageable pageable);

    @Query("SELECT c FROM Cliente c WHERE c.fatturatoAnnuale > 100000.00")
    Page<Cliente> findByFatturatoAnnualeGreaterThan100000(Pageable pageable);

}