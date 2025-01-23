import { iClienti } from './i-clienti';

export interface iFatture {
  id: number;
  data: string;
  importo: number;
  numero: string;
  statoFattura: StatoFattura;
  cliente: iClienti;
}

export interface StatoFattura {
  id: number;
  nome: string;
}
