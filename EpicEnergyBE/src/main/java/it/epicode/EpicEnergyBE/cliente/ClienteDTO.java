package it.epicode.EpicEnergyBE.cliente;

import it.epicode.EpicEnergyBE.indirizzo.Indirizzo;
import lombok.Data;

import java.time.LocalDate;

    @Data
    public class ClienteDTO {
        private String ragioneSociale;
        private String partitaIva;
        private String email;
        private LocalDate dataInserimento;
        private LocalDate dataUltimoContatto;
        private Double fatturatoAnnuale;
        private String pec;
        private String telefono;
        private String emailContatto;
        private String nomeContatto;
        private String cognomeContatto;
        private String telefonoContatto;
        private TipoCliente tipoCliente;
        private Indirizzo sedeLegale;
        private Indirizzo sedeOperativa;
    }

