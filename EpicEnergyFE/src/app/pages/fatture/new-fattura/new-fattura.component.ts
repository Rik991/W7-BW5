import { ClientiService } from './../../../services/clienti.service';
import { FattureService } from './../../../services/fatture.service';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';
import { iFatturaRequest } from '../../../interfaces/i-fattura-request';
import { iStatoFattura } from '../../../interfaces/i-fatture';
import { iCliente } from '../../../interfaces/i-clienti';

@Component({
  selector: 'app-new-fattura',
  templateUrl: './new-fattura.component.html',
  styleUrls: ['./new-fattura.component.scss'],
})
export class NewFatturaComponent implements OnInit {
  form!: FormGroup;
  statoFatture: iStatoFattura[] = [];
  clienti: iCliente[] = [];
  clienteSelezionato?: iCliente;
  numeroFattura: number = 0;

  @ViewChild('fattura', { static: false }) fatturaElement!: ElementRef;

  constructor(
    private fb: FormBuilder,
    private fattureService: FattureService,
    private clientiService: ClientiService
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      ragioneSociale: ['', Validators.required],
      importoFattura: ['', [Validators.required, Validators.required]],
      dataFatturazione: ['', Validators.required],
      statoFattura: ['', Validators.required],
    });
    this.getAllClienti();
    this.getAllStatoFattura();
    this.getUltimoNumeroFattura();
  }

  getUltimoNumeroFattura(): void {
    this.fattureService.getUltimoNumeroFattura().subscribe({
      next: (response) => {
        this.numeroFattura = response + 1; // Incrementa l'ultimo numero di fattura
      },
      error: (error) => {
        console.error(
          "Errore nel recupero dell'ultimo numero di fattura:",
          error
        );
      },
    });
  }

  createStatoFattura(nome: string) {
    this.fattureService.createStatoFattura(nome).subscribe({
      next: (response) => {
        this.getAllStatoFattura();
        this.form.patchValue({ statoFattura: response.nome });
      },
      error: (error) => {
        console.error('Errore nella creazione dello stato Fattura:', error);
        alert('Errore nella creazione dello stato fattura. Riprovare.');
      },
    });
  }

  onStatoFatturaChange(event: Event) {
    const selectElement = event.target as HTMLSelectElement;

    // Verifica se l'opzione selezionata è "Aggiungi nuovo stato..."
    if (selectElement.value === 'new') {
      const nuovoStato = prompt('Inserisci il nome del nuovo stato:');

      // Controlla che l'utente abbia effettivamente inserito un valore
      if (nuovoStato && nuovoStato.trim() !== '') {
        this.createStatoFattura(nuovoStato.trim());
      } else {
        alert('Il nome dello stato non può essere vuoto.');
      }
    }
  }

  onClienteChange(event: Event) {
    const selectElement = event.target as HTMLSelectElement;
    const ragioneSociale = selectElement.value;
    this.clienteSelezionato = this.clienti.find(
      (cliente) => cliente.ragioneSociale === ragioneSociale
    );
  }

  getAllStatoFattura() {
    this.fattureService.getAllStatoFattura().subscribe({
      next: (response) => {
        console.log('Stati Fattura:', response);
        this.statoFatture = response;
      },
      error: (error) => {
        console.error('Errore nel recupero degli stati Fattura:', error);
      },
    });
  }

  getAllClienti() {
    this.clientiService.getAllClienti().subscribe({
      next: (data) => {
        console.log('Clienti ricevuti:', data); // Controlla che sia un array
        this.clienti = data;
      },
      error: (error) => {
        console.error('Errore nel recupero dei clienti:', error);
      },
    });
  }

  createFattura() {
    if (this.form.valid) {
      const fatturaDto: iFatturaRequest = {
        data: this.form.value.dataFatturazione,
        importo: Number(this.form.value.importoFattura),
        numero: '', // Numero fattura lasciato vuoto
        statoFatturaNome: this.form.value.statoFattura,
      };
      this.fattureService
        .createFattura(this.form.value.ragioneSociale, fatturaDto)
        .subscribe({
          next: (response) => {
            console.log('Fattura creata con successo:', response);
          },
          error: (error) => {
            console.error('Errore nella creazione della fattura:', error);
          },
        });
    }
  }

  downloadPDF() {
    const data = this.fatturaElement.nativeElement;
    html2canvas(data).then((canvas) => {
      const imgWidth = 208;
      const imgHeight = (canvas.height * imgWidth) / canvas.width;
      const contentDataURL = canvas.toDataURL('image/png');
      const pdf = new jsPDF('p', 'mm', 'a4');
      const position = 0;
      pdf.addImage(contentDataURL, 'PNG', 0, position, imgWidth, imgHeight);
      pdf.save('fattura.pdf');
    });
  }
}
