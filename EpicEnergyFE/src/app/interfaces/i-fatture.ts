import { iClienti } from './i-clienti';

export interface iFattura {
  id: number;
  data: string;
  importo: number;
  numero: string;
  statoFattura: iStatoFattura;
  cliente: iClienti;
}

export interface iStatoFattura {
  id: number;
  nome: string;
}
