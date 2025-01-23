import { FattureService } from './../../services/fatture.service';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ClientiService } from '../../services/clienti.service';
import { iFattura } from '../../interfaces/i-fatture';

@Component({
  selector: 'app-fatture',
  templateUrl: './fatture.component.html',
  styleUrl: './fatture.component.scss',
})
export class FattureComponent implements OnInit {
  form!: FormGroup;
  fatture: iFattura[] = [];

  @ViewChild('fattura', { static: false }) fatturaElement!: ElementRef;

  constructor(
    private fb: FormBuilder,
    private fattureService: FattureService,
    private clientiService: ClientiService
  ) {
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      ragioneSociale: [''],
      importoFattura: [''],
      dataFatturazione: [''],
      statoFattura: [''],
    });
  }


  findByFattureRagioneSociale(ragioneSociale: string) {
    this.fattureService.findByClienteRagioneSAociale(ragioneSociale).subscribe({
      next: (response) => {
        this.fatture = response;
        console.log(response);
      },
      error: (error) => {
        console.error('Errore nella ricerca delle fatture:', error);
        alert('Errore nella ricerca delle fatture. Riprovare.');
      },
    });
  }
}
