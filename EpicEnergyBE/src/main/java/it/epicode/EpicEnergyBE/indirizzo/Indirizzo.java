package it.epicode.EpicEnergyBE.indirizzo;

import it.epicode.EpicEnergyBE.provincia.comune.Comune;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "indirizzi")
public class Indirizzo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Il campo via non può essere vuoto!")
    private String via;

    @NotBlank(message = "Il campo numero civico non può essere vuoto!")
    private String civico;

    private String localita;

    @NotBlank(message = "Il campo CAP non può essere vuoto!")
    private String cap;

    @ManyToOne
    @JoinColumn(name = "comune_id")
    private Comune comune;

}