import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { iCliente } from '../interfaces/i-clienti';
import { iPageClienti } from '../interfaces/i-page-clienti';

@Injectable({
  providedIn: 'root',
})
export class ClientiService {
  clientiUrl: string = environment.clientiUrl;
  clientiByRagioneSocialeUrl: string = environment.clientiByRagioneSocialeUrl;
  clientiByRangeDataInserimentoUrl: string =
    environment.clientiByRangeDataInserimentoUrl;
  clientiByRangeFatturatoAnnualeUrl: string =
    environment.clientiByRangeFatturatoAnnualeUrl;
  clientiByDataUltimoContattoUrl: string =
    environment.clientiByDataUltimoContattoUrl;

  constructor(private http: HttpClient) {}

  registerClienti(
    clientData: Partial<iCliente>,
    logoAziendale?: File
  ): Observable<iCliente> {
    const formData = new FormData();
    formData.append('ragioneSociale', clientData.ragioneSociale || '');
    formData.append('partitaIva', clientData.partitaIva || '');
    formData.append('email', clientData.email || '');
    formData.append('dataUltimoContatto', clientData.dataUltimoContatto || '');
    formData.append(
      'fatturatoAnnuale',
      clientData.fatturatoAnnuale?.toString() || ''
    );
    formData.append('pec', clientData.pec || '');
    formData.append('telefono', clientData.telefono || '');
    formData.append('emailContatto', clientData.emailContatto || '');
    formData.append('nomeContatto', clientData.nomeContatto || '');
    formData.append('cognomeContatto', clientData.cognomeContatto || '');
    formData.append('telefonoContatto', clientData.telefonoContatto || '');
    formData.append('tipoCliente', clientData.tipoCliente?.toString() || '');
    formData.append('viaSedeLegale', clientData.sedeLegale?.via || '');
    formData.append('civicoSedeLegale', clientData.sedeLegale?.civico || '');
    formData.append(
      'localitaSedeLegale',
      clientData.sedeLegale?.localita || ''
    );
    formData.append('capSedeLegale', clientData.sedeLegale?.cap || '');
    formData.append('comuneSedeLegale', clientData.sedeLegale?.comune || '');
    formData.append('viaSedeOperativa', clientData.sedeOperativa?.via || '');
    formData.append(
      'civicoSedeOperativa',
      clientData.sedeOperativa?.civico || ''
    );
    formData.append(
      'localitaSedeOperativa',
      clientData.sedeOperativa?.localita || ''
    );
    formData.append('capSedeOperativa', clientData.sedeOperativa?.cap || '');
    formData.append(
      'comuneSedeOperativa',
      clientData.sedeOperativa?.comune || ''
    );
    if (logoAziendale) {
      formData.append('logoAziendale', logoAziendale);
    }
    return this.http.post<iCliente>(this.clientiUrl, formData);
  }

  getAllClienti(): Observable<iCliente[]> {
    return this.http.get<iCliente[]>(this.clientiUrl);
  }

  getClienti(page: number): Observable<iPageClienti> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', '10');

    return this.http.get<iPageClienti>(this.clientiUrl, { params });
  }

  getClientiById(id: number): Observable<iCliente> {
    return this.http.get<iCliente>(`${this.clientiUrl}/${id}`);
  }

  deleteClienti(id: number): Observable<void> {
    return this.http.delete<void>(`${this.clientiUrl}/${id}`);
  }

  updateClienti(
    id: number,
    clientData: Partial<iCliente>,
    logoAziendale?: File
  ): Observable<iCliente> {
    const formData = new FormData();
    formData.append('ragioneSociale', clientData.ragioneSociale || '');
    formData.append('partitaIva', clientData.partitaIva || '');
    formData.append('email', clientData.email || '');
    formData.append('dataUltimoContatto', clientData.dataUltimoContatto || '');
    formData.append(
      'fatturatoAnnuale',
      clientData.fatturatoAnnuale?.toString() || ''
    );
    formData.append('pec', clientData.pec || '');
    formData.append('telefono', clientData.telefono || '');
    formData.append('emailContatto', clientData.emailContatto || '');
    formData.append('nomeContatto', clientData.nomeContatto || '');
    formData.append('cognomeContatto', clientData.cognomeContatto || '');
    formData.append('telefonoContatto', clientData.telefonoContatto || '');
    formData.append('tipoCliente', clientData.tipoCliente?.toString() || '');
    formData.append('viaSedeLegale', clientData.sedeLegale?.via || '');
    formData.append('civicoSedeLegale', clientData.sedeLegale?.civico || '');
    formData.append(
      'localitaSedeLegale',
      clientData.sedeLegale?.localita || ''
    );
    formData.append('capSedeLegale', clientData.sedeLegale?.cap || '');
    formData.append('comuneSedeLegale', clientData.sedeLegale?.comune || '');
    formData.append('viaSedeOperativa', clientData.sedeOperativa?.via || '');
    formData.append(
      'civicoSedeOperativa',
      clientData.sedeOperativa?.civico || ''
    );
    formData.append(
      'localitaSedeOperativa',
      clientData.sedeOperativa?.localita || ''
    );
    formData.append('capSedeOperativa', clientData.sedeOperativa?.cap || '');
    formData.append(
      'comuneSedeOperativa',
      clientData.sedeOperativa?.comune || ''
    );
    if (logoAziendale) {
      formData.append('logoAziendale', logoAziendale);
    }
    return this.http.put<iCliente>(`${this.clientiUrl}/${id}`, formData);
  }

  getByRagioneSociale(ragioneSociale: string): Observable<iCliente> {
    let params = new HttpParams().set('ragioneSociale', ragioneSociale);
    return this.http.get<iCliente>(this.clientiByRagioneSocialeUrl, { params });
  }

  getClientiByRangeDataInserimento(
    dataIniziale: string,
    dataFinale: string
  ): Observable<iPageClienti> {
    let params = new HttpParams()
      .set('dataIniziale', dataIniziale)
      .set('dataFinale', dataFinale);
    return this.http.get<iPageClienti>(this.clientiByRangeDataInserimentoUrl, {
      params,
    });
  }

  getClientiByRangeFatturatoAnnuale(
    fatturatoMin: number,
    fatturatoMax: number
  ): Observable<iPageClienti> {
    let params = new HttpParams()
      .set('fatturatoMin', fatturatoMin.toString())
      .set('fatturatoMax', fatturatoMax.toString());
    return this.http.get<iPageClienti>(this.clientiByRangeFatturatoAnnualeUrl, {
      params,
    });
  }

  getclientiByDataUltimoContatto(
    dataIniziale: string,
    dataFinale: string
  ): Observable<iPageClienti> {
    let params = new HttpParams()
      .set('dataIniziale', dataIniziale)
      .set('dataFinale', dataFinale);
    return this.http.get<iPageClienti>(this.clientiByDataUltimoContattoUrl, {
      params,
    });
  }
}
