package it.epicode.EpicEnergyBE.provincia.comune;

import it.epicode.EpicEnergyBE.provincia.Provincia;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "comuni")
public class Comune {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String codiceProvincia;
    private String progressivoComune;
    private String denominazione;

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;


}