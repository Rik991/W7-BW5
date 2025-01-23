import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { iClienti } from '../interfaces/i-clienti';
import { iPageClienti } from '../interfaces/i-page-clienti';

@Injectable({
  providedIn: 'root',
})
export class ClientiService {
  clientiUrl: string = environment.clientiUrl;

  constructor(private http: HttpClient) {}

  registerClienti(
    clientData: Partial<iClienti>,
    logoAziendale?: File
  ): Observable<iClienti> {
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
    return this.http.post<iClienti>(this.clientiUrl, formData);
  }

  getClienti(page: number): Observable<iPageClienti> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', '10');

    return this.http.get<iPageClienti>(this.clientiUrl, { params });
  }

  getClientiById(id: number): Observable<iClienti> {
    return this.http.get<iClienti>(`${this.clientiUrl}/${id}`);
  }

  deleteClienti(id: number): Observable<void> {
    return this.http.delete<void>(`${this.clientiUrl}/${id}`);
  }

  updateClienti(
    id: number,
    clientData: Partial<iClienti>,
    logoAziendale?: File
  ): Observable<iClienti> {
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
    return this.http.put<iClienti>(`${this.clientiUrl}/${id}`, formData);
  }

  getByRagioneSociale(ragioneSociale: string): Observable<iClienti> {
    let params = new HttpParams().set('ragioneSociale', ragioneSociale);
    return this.http.get<iClienti>(this.clientiUrl, { params });
  }
}
