package it.epicode.EpicEnergyBE.fattura;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FatturaDTO {
    private LocalDate data;
    private Double importo;
    private String numero;
    private Long clienteId;
    private String statoFatturaNome;

}