import { FattureService } from './../../../services/fatture.service';
import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';
import { iFatturaRequest } from '../../../interfaces/i-fattura-request';

@Component({
  selector: 'app-new-fattura',
  templateUrl: './new-fattura.component.html',
  styleUrl: './new-fattura.component.scss',
})
export class NewFatturaComponent {
  form!: FormGroup;

  @ViewChild('fattura', { static: false }) fatturaElement!: ElementRef;

  constructor(private fb: FormBuilder, private fattureService: FattureService) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      ragioneSociale: [''],
      importoFattura: [''],
      numeroFattura: [''],
      dataFatturazione: [''],
      statoFattura: [''],
    });
  }

  createFattura() {
    if (this.form.valid) {
      const fatturaRequest: iFatturaRequest = {
        data: this.form.value.dataFatturazione,
        importo: Number(this.form.value.importoFattura),
        numero: "10",
        clienteId: 1, // Qui dovresti passare l'ID del cliente selezionato
        statoFatturaNome: this.form.value.statoFattura
      };

      this.fattureService.createFattura(fatturaRequest).subscribe({
        next: (response) => {
          console.log('Fattura creata con successo:', response);
          // Aggiungi qui la logica post-creazione (es. redirect, messaggio di successo)
        },
        error: (error) => {
          console.error('Errore nella creazione della fattura:', error);
          // Gestione dell'errore
        }
      });
    }
  }

  //grazie al @viewChild possiamo accedere al nostro elemento html e quindi creare un pdf
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
