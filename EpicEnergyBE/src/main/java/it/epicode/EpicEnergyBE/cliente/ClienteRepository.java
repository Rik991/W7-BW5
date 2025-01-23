package it.epicode.EpicEnergyBE.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByRagioneSociale(String ragioneSociale);

    @Query("SELECT c FROM Cliente c WHERE c.fatturatoAnnuale BETWEEN :minImporto AND :maxImporto")
    Page<Cliente> findByFatturatoAnnualeRange(@Param("minImporto") Double minImporto,@Param("maxImporto") Double maxImporto, Pageable pageable);

    @Query("SELECT c FROM Cliente c WHERE c.dataInserimento BETWEEN :dataInizio AND :dataFine")
    Page<Cliente> findByDataInserimentoBetween(@Param("dataInizio") LocalDate dataInizio, @Param("dataFine") LocalDate dataFine, Pageable pageable);

    @Query("SELECT c FROM Cliente c WHERE c.dataUltimoContatto BETWEEN :dataInizio AND :dataFine")
    Page<Cliente> findByDataUltimoContattoBetween(@Param("dataInizio") LocalDate dataInizio, @Param("dataFine") LocalDate dataFine, Pageable pageable);

    @Query("SELECT c FROM Cliente c WHERE LOWER(c.ragioneSociale) LIKE LOWER(CONCAT('%', :ragioneSociale, '%'))")
    Cliente findByRagioneSocialeContains(@Param("ragioneSociale") String ragioneSociale);

}