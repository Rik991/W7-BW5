<<<<<<< HEAD
import { iClienti } from './i-clienti';
=======
import { iCliente } from './i-clienti';
>>>>>>> feature-clienteFE

export interface iFattura {
  id: number;
  data: string;
  importo: number;
  numero: string;
  statoFattura: iStatoFattura;
<<<<<<< HEAD
  cliente: iClienti;
=======
  cliente: iCliente;
>>>>>>> feature-clienteFE
}

export interface iStatoFattura {
  id: number;
  nome: string;
}
