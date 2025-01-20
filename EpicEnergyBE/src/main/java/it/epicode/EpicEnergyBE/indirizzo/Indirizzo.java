package it.epicode.EpicEnergyBE.indirizzo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "indirizzi")
public class Indirizzo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String via;
    private String civico;
    private String localita;
    private String cap;

//    @ManyToOne
//    private Comune comune;

}