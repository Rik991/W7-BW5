package it.epicode.EpicEnergyBE.fattura.stato_fattura;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "stati_fattura")
public class StatoFattura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;
}