import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

import { iFatturaRequest } from '../interfaces/i-fattura-request';
import { Observable } from 'rxjs';
import { iFattura, iStatoFattura } from '../interfaces/i-fatture';

@Injectable({
  providedIn: 'root',
})
export class FattureService {
  fattureUrl: string = environment.fattureUrl;
  StatoFatturaUrl: string = environment.statoFatturaUrl;

  constructor(private http: HttpClient) {}

  getAllStatoFattura(): Observable<iStatoFattura[]> {
    return this.http.get<iStatoFattura[]>(this.StatoFatturaUrl);
  }

  createStatoFattura(nome: string) {
    const params = new HttpParams().set('nome', nome);
    return this.http.post<iStatoFattura>(environment.statoFatturaUrl, null, {
      params,
    });
  }

  createFattura(
    ragioneSociale: string,
    fatturaDto: iFatturaRequest
  ): Observable<iFattura> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<iFattura>(
      `${this.fattureUrl}?ragioneSociale=${ragioneSociale}`,
      fatturaDto,
      { headers }
    );
  }
}
