package it.epicode.EpicEnergyBE.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByRagioneSociale(String ragioneSociale);


    // Query method
    Page<Cliente> findAllByOrderByNomeContatto(Pageable pageable);
    Page<Cliente> findAllByOrderByFatturatoAnnuale(Pageable pageable);
    Page<Cliente> findAllByOrderByDataInserimento(Pageable pageable);
    Page<Cliente> findAllByOrderByDataUltimoContatto(Pageable pageable);
    @Query("SELECT c FROM Cliente c JOIN c.sedeLegale s JOIN s.comune com JOIN com.provincia p ORDER BY p.nome")
    Page<Cliente> findAllByOrderByProvinciaSedeLegale(Pageable pageable);
}