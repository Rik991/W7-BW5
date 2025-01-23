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

  onPageChange(page: number, key: number): void {
    this.currentPage = page;
    this.getAll(page);
  }

  ngOnInit(): void {
    // this.loadPage(this.currentPage);
  }

  getAll(page: number): void {
    const currentPage = page - 1;
    this.clientiSvc.getClienti(currentPage).subscribe((pageClienti) => {
      this.pageClienti = pageClienti;
      this.clientiArray = pageClienti.content;
    });
  }

  getByRagione(ragioneSociale: string, page: number): void {
    const currentPage = page - 1;
    this.clientiSvc
      .getByRagioneSociale(ragioneSociale, currentPage)
      .subscribe((pageClienti) => {
        this.pageClienti = pageClienti;
        this.clientiArray = pageClienti.content;
        console.log(pageClienti);
      });
  }

  getByRangeDataInserimento(
    dataInizio: string,
    dataFine: string,
    page: number
  ): void {
    const currentPage = page - 1;
    this.clientiSvc
      .getClientiByRangeDataInserimento(dataInizio, dataFine, currentPage)
      .subscribe((pageClienti) => {
        this.pageClienti = pageClienti;
        this.clientiArray = pageClienti.content;
      });
  }

  getByRangeFatturatoAnnuale(min: string, max: string, page: number): void {
    const currentPage = page - 1;
    this.clientiSvc
      .getClientiByRangeFatturatoAnnuale(min, max, currentPage)
      .subscribe((pageClienti) => {
        this.pageClienti = pageClienti;
        this.clientiArray = pageClienti.content;
      });
  }

  getByDataUltimoContatto(
    dataInizio: string,
    dataFine: string,
    page: number
  ): void {
    const currentPage = page - 1;
    this.clientiSvc
      .getclientiByDataUltimoContatto(dataInizio, dataFine, currentPage)
      .subscribe((pageClienti) => {
        this.pageClienti = pageClienti;
        this.clientiArray = pageClienti.content;
      });
  }
}
