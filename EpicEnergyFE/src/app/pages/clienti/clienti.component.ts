import { Component } from '@angular/core';
import { ClientiService } from '../../services/clienti.service';
import { iPageClienti } from '../../interfaces/i-page-clienti';
import { iCliente } from '../../interfaces/i-clienti';

@Component({
  selector: 'app-clienti',
  templateUrl: './clienti.component.html',
  styleUrl: './clienti.component.scss',
})
export class ClientiComponent {
  constructor(private clientiSvc: ClientiService) {}

  pageClienti!: iPageClienti;
  clientiArray: iCliente[] = [];
  currentPage: number = 1;

  key: number = 1;

  ragioneSociale!: string;
  dataUltimoIn!: string;
  dataUltimoFin!: string;

  dataRangeIn!: string;
  dataRangeFin!: string;

  fatturatoMin!: string;
  fatturatoMax!: string;

  onPageChange(page: number): void {
    this.currentPage = page;
    switch (this.key) {
      case 1:
        this.getAll(page);
        break;
      case 2:
        this.getByRagione(this.ragioneSociale, page);
        break;
      case 3:
        this.getByDataUltimoContatto(
          this.dataUltimoIn,
          this.dataUltimoFin,
          page
        );
        break;
      case 4:
        this.getByRangeDataInserimento(
          this.dataRangeIn,
          this.dataRangeFin,
          page
        );
        break;
      case 5:
        this.getByRangeFatturatoAnnuale(
          this.fatturatoMin,
          this.fatturatoMax,
          page
        );
        break;
    }
  }

  resetPage(key: number): void {
    this.currentPage = 1;
    this.key = key;
  }

  ngOnInit(): void {
    // this.loadPage(this.currentPage);
  }

  getAll(page: number): void {
    const currentPage = page - 1;
    this.clientiSvc.getClienti(currentPage).subscribe((pageClienti) => {
      this.pageClienti = pageClienti;
      this.clientiArray = pageClienti.content;
      console.log('all', pageClienti);
    });
  }

  getByRagione(ragioneSociale: string, page: number): void {
    this.ragioneSociale = ragioneSociale;
    const currentPage = page - 1;
    this.clientiSvc
      .getByRagioneSociale(ragioneSociale, currentPage)
      .subscribe((pageClienti) => {
        this.pageClienti = pageClienti;
        this.clientiArray = pageClienti.content;
        console.log('ragione', pageClienti);
      });
  }

  getByRangeDataInserimento(
    dataInizio: string,
    dataFine: string,
    page: number
  ): void {
    this.dataRangeIn = dataInizio;
    this.dataRangeFin = dataFine;
    const currentPage = page - 1;
    this.clientiSvc
      .getClientiByRangeDataInserimento(dataInizio, dataFine, currentPage)
      .subscribe((pageClienti) => {
        this.pageClienti = pageClienti;
        this.clientiArray = pageClienti.content;
        console.log('rangedataIns', pageClienti);
      });
  }

  getByRangeFatturatoAnnuale(min: string, max: string, page: number): void {
    this.fatturatoMin = min;
    this.fatturatoMax = max;
    const currentPage = page - 1;
    this.clientiSvc
      .getClientiByRangeFatturatoAnnuale(min, max, currentPage)
      .subscribe((pageClienti) => {
        this.pageClienti = pageClienti;
        this.clientiArray = pageClienti.content;
        console.log('fatturato', pageClienti);
      });
  }

  getByDataUltimoContatto(
    dataInizio: string,
    dataFine: string,
    page: number
  ): void {
    this.dataUltimoIn = dataInizio;
    this.dataUltimoFin = dataFine;
    const currentPage = page - 1;
    this.clientiSvc
      .getclientiByDataUltimoContatto(dataInizio, dataFine, currentPage)
      .subscribe((pageClienti) => {
        this.pageClienti = pageClienti;
        this.clientiArray = pageClienti.content;
        console.log('ultimo cont', pageClienti);
      });
  }
}
