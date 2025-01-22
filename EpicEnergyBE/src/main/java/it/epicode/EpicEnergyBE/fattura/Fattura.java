package it.epicode.EpicEnergyBE.fattura;

import it.epicode.EpicEnergyBE.cliente.Cliente;
import it.epicode.EpicEnergyBE.fattura.stato_fattura.StatoFattura;
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

    @Column(unique = true)
    private String numero;

    @ManyToOne
    @JoinColumn(name = "stato_fattura_id")
    private StatoFattura statoFattura;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}