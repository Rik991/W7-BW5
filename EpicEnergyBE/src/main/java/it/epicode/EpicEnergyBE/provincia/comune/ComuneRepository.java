package it.epicode.EpicEnergyBE.provincia.comune;

import it.epicode.EpicEnergyBE.provincia.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComuneRepository extends JpaRepository<Comune, Long> {

    List<Comune> findByProvincia(Provincia provincia);

}