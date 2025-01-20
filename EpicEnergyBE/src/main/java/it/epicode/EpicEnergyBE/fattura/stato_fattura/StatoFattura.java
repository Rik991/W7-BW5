package it.epicode.EpicEnergyBE.fattura.stato_fattura;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "stato_fatture")
public class StatoFattura {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;
}