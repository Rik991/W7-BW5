import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { iFatturaRequest } from '../interfaces/i-fattura-request';
import { Observable } from 'rxjs';
import { iFattura } from '../interfaces/i-fatture';

@Injectable({
  providedIn: 'root',
})
export class FattureService {
  fattureUrl: string = environment.fattureUrl;

  constructor(private http: HttpClient) {}

  createFattura(
    ragioneSociale: string,
    fatturaData: iFatturaRequest
  ): Observable<iFattura> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<iFattura>(
      `${this.fattureUrl}?ragioneSociale=${ragioneSociale}`,
      fatturaData,
      { headers }
    );
  }
}
