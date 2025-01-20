package it.epicode.EpicEnergyBE.indirizzo;

import lombok.Data;

@Data
public class IndirizzoDTO {
    private String via;
    private String civico;
    private String localita;
    private String cap;
    private Long comuneId;
}

