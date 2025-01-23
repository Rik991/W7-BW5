import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { iFatture } from '../interfaces/i-fatture';
import { iFatturaRequest } from '../interfaces/i-fattura-request';

@Injectable({
  providedIn: 'root'
})
export class FattureService {

  fattureUrl: string = environment.fattureUrl;

  constructor(private http: HttpClient) { }

  createFattura(fatturaData: iFatturaRequest) {
    return this.http.post<iFatture>(this.fattureUrl, fatturaData);
  }
}
