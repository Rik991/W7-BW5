package it.epicode.EpicEnergyBE.indirizzo;

import it.epicode.EpicEnergyBE.provincia.comune.Comune;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "indirizzi")
public class Indirizzo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String via;
    private String civico;
    private String localita;
    private String cap;

    @ManyToOne
    @JoinColumn(name = "comune_id")
    private Comune comune;

}