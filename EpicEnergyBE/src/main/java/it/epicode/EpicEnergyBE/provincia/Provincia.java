package it.epicode.EpicEnergyBE.provincia;

import it.epicode.EpicEnergyBE.provincia.comune.Comune;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "province")
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String sigla;
    private String nome;
    private String regione;


}