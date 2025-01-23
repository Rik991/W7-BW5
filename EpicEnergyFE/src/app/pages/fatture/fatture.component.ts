import { FattureService } from './../../services/fatture.service';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ClientiService } from '../../services/clienti.service';
import { iFattura } from '../../interfaces/i-fatture';
import { IFilterFatture } from '../../interfaces/i-filter-fatture';
import { iPageFatture } from '../../interfaces/i-page-fatture';

@Component({
  selector: 'app-fatture',
  templateUrl: './fatture.component.html',
  styleUrl: './fatture.component.scss',
})
export class FattureComponent implements OnInit {
  form!: FormGroup;
  fatture!: IFilterFatture;
  currentPage: number = 1;
  fattureArray: iFattura[] = [];
  pageFatture!: iPageFatture;

  @ViewChild('fattura', { static: false }) fatturaElement!: ElementRef;

  constructor(
    private fb: FormBuilder,
    private fattureService: FattureService,
    private clientiService: ClientiService
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      ragioneSociale: [''],
      importoFattura: [''],
      dataFatturazione: [''],
      statoFattura: [''],
    });

    this.fattureService.filtroFatture$.subscribe((fatture) => {
      this.fatture = fatture;
    });

    this.onPageChange(this.currentPage);
  }

  onPageChange(page: number): void {
    this.currentPage = page;
    switch (this.fatture.key) {
      case 1:
        this.getAllFatture(page);
        break;
      case 2:
        this.getByRagioneSociale(this.fatture.ragioneSociale, page);
        break;
      // case 3:
      //   this.getByDataUltimoContatto(
      //     this.filterData.dataIniziale,
      //     this.filterData.dataFinale,
      //     page
      //   );
      //   break;
      // case 4:
      //   this.getByRangeDataInserimento(
      //     this.filterData.dataIniziale,
      //     this.filterData.dataFinale,
      //     page
      //   );
      //   break;
      // case 5:
      //   this.getByRangeFatturatoAnnuale(
      //     this.filterData.fatturatoMin,
      //     this.filterData.fatturatoMax,
      //     page
      //   );
      // break;
    }
  }

  getAllFatture(page: number): void {
    const currentPage = page - 1;
    this.fattureService.getAllFatture(currentPage).subscribe((pageFatture) => {
      this.pageFatture = pageFatture;
      this.fattureArray = pageFatture.content;
    });
  }

  getByRagioneSociale(ragioneSociale: string, page: number): void {
    const currentPage = page - 1;
    this.fattureService
      .getByRagioneSociale(ragioneSociale, currentPage)
      .subscribe((pageFatture) => {
        this.pageFatture = pageFatture;
        this.fattureArray = pageFatture.content;
      });
  }
}
