package it.epicode.EpicEnergyBE.fattura;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface FatturaRepository extends JpaRepository<Fattura, Long> {
    Optional<Fattura> findByNumero(String numero);

    void deleteByNumero(String numero);

    @Query("SELECT f FROM Fattura f WHERE LOWER(f.cliente.ragioneSociale) LIKE LOWER(CONCAT('%', :ragioneSociale, '%'))")
    Page<Fattura> findFattureCliente(@Param("ragioneSociale") String ragioneSociale, Pageable pageable);

    @Query("SELECT f FROM Fattura f WHERE f.statoFattura.nome = :statoFatturaNome")
    Page<Fattura> findFattureByStatoFattura(@Param("statoFatturaNome") String statoFatturaNome, Pageable pageable);

    @Query("SELECT f FROM Fattura f WHERE f.data BETWEEN :dataInizio AND :dataFine")
    Page<Fattura> findFattureByData(@Param("dataInizio") LocalDate dataInizio, @Param("dataFine") LocalDate dataFine, Pageable pageable);

    @Query("SELECT f FROM Fattura f WHERE YEAR(f.data) = :anno")
    Page<Fattura> findFattureByAnno(@Param("anno") int anno, Pageable pageable);

    @Query("SELECT f FROM Fattura f WHERE f.importo BETWEEN :minImporto AND :maxImporto")
    Page<Fattura> findFattureByImportoRange(@Param("minImporto") Double minImporto, @Param("maxImporto") Double maxImporto, Pageable pageable);

    @Query("SELECT MAX(f.numero) FROM Fattura f")
    Integer findUltimoNumeroFattura();

}
