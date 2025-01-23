package it.epicode.EpicEnergyBE.fattura.stato_fattura;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "stati_fattura")
public class StatoFattura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Il campo stato fattura non può essere vuoto!")
    @Column(unique = true)
    private String nome;
}