package it.epicode.EpicEnergyBE.cliente;

import it.epicode.EpicEnergyBE.indirizzo.Indirizzo;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "clienti")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Il campo ragione sociale non può essere vuoto!")
    @Column(name = "ragione_sociale", unique = true)
    private String ragioneSociale;

    @NotBlank(message = "Il campo partita iva non può essere vuoto!")
    @Column(name = "partita_iva", unique = true)
    private String partitaIva;

    @NotBlank(message = "Il campo email non può essere vuoto!")
    @Email(message = "Inserire una mail valida")
    @Column(unique = true)
    private String email;

    @Column(name = "data_inserimento")
    private LocalDate dataInserimento;

    @PastOrPresent(message = "la data non può essere nel futuro")
    @Column(name = "data_ultimo_contatto")
    private LocalDate dataUltimoContatto;

    @PositiveOrZero(message = "Inserire soltanto cifre positive")
    @Column(name = "fatturato_annuale")
    private Double fatturatoAnnuale;

    @NotBlank(message = "Il campo email non può essere vuoto!")
    @Email(message = "Inserire una mail valida")
    @Column(unique = true)
    private String pec;

    @Column(unique = true)
    private String telefono;

    @Email(message = "Inserire una mail valida")
    @Column(name = "email_contatto")
    private String emailContatto;

    @Column(name = "nome_contatto")
    private String nomeContatto;

    @Column(name = "cognome_contatto")
    private String cognomeContatto;

    @Column(name = "telefono_contatto")
    private String telefonoContatto;

    @Column(name = "logo_aziendale",unique = true)
    private String logoAziendale;

    @NotBlank(message = "il campo tipo cliente non può essere vuoto")
    @Column(name = "tipo_cliente")
    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;

    @OneToOne(cascade = CascadeType.ALL)
    private Indirizzo sedeLegale;

    @OneToOne(cascade = CascadeType.ALL)
    private Indirizzo sedeOperativa;


}