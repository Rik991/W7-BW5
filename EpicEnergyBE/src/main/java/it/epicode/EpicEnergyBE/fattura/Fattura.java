package it.epicode.EpicEnergyBE.fattura;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "fatture")
public class Fattura {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private LocalDate data;
    private Double importo;
    private String numero;

//    @ManyToOne
//    private StatoFattura stato;
//
//    @ManyToOne
//    private Cliente cliente;
}