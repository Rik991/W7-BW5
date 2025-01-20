package it.epicode.EpicEnergyBE.cliente;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "clienti")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "ragione_sociale")
    private String ragioneSociale;

    @Column(name = "partita_iva")
    private String partitaIva;

    private String email;

    @Column(name = "data_inserimento")
    private LocalDate dataInserimento;

    @Column(name = "data_ultimo_contatto")
    private LocalDate dataUltimoContatto;

    @Column(name = "fatturato_annuale")
    private Double fatturatoAnnuale;

    private String pec;
    private String telefono;

    @Column(name = "email_contatto")
    private String emailContatto;

    @Column(name = "nome_contatto")
    private String nomeContatto;

    @Column(name = "cognome_contatto")
    private String cognomeContatto;

    @Column(name = "telefono_contatto")
    private String telefonoContatto;

    @Column(name = "logo_aziendale")
    private String logoAziendale;
//
//    @Enumerated(EnumType.STRING)
//    private TipoCliente tipo;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    private Indirizzo sedeLegale;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    private Indirizzo sedeOperativa;
//
//    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
//    private List<Fattura> fatture;


}