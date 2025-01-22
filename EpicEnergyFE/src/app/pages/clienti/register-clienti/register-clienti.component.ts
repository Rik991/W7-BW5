import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ClientiService } from '../../../services/clienti.service';
import { iClienti } from '../../../interfaces/i-clienti';

@Component({
  selector: 'app-register-clienti',
  templateUrl: './register-clienti.component.html',
  styleUrls: ['./register-clienti.component.scss'],
})
export class RegisterClientiComponent implements OnInit {
  form!: FormGroup;
  logoAziendaleFile: File | undefined;

  constructor(
    private fb: FormBuilder,
    private clientiService: ClientiService,
    private router: Router
  ) {}

  ngOnInit() {
    this.form = this.fb.group({
      ragioneSociale: [''],
      partitaIva: [''],
      email: [''],
      dataUltimoContatto: [''],
      fatturatoAnnuale: [''],
      pec: [''],
      telefono: [''],
      emailContatto: [''],
      nomeContatto: [''],
      cognomeContatto: [''],
      telefonoContatto: [''],
      tipoCliente: [''],
      sedeLegale: this.fb.group({
        via: [''],
        civico: [''],
        localita: [''],
        cap: [''],
        comune: [''],
      }),
      sedeOperativa: this.fb.group({
        via: [''],
        civico: [''],
        localita: [''],
        cap: [''],
        comune: [''],
      }),
    });
  }

  onFileChange(event: any) {
    if (event.target.files.length > 0) {
      this.logoAziendaleFile = event.target.files[0];
    }
  }

  registerClienti() {
    if (this.form.valid) {
      const formData: Partial<iClienti> = {
        ragioneSociale: this.form.value.ragioneSociale,
        partitaIva: this.form.value.partitaIva,
        email: this.form.value.email,
        dataUltimoContatto: this.form.value.dataUltimoContatto,
        fatturatoAnnuale: this.form.value.fatturatoAnnuale,
        pec: this.form.value.pec,
        telefono: this.form.value.telefono,
        emailContatto: this.form.value.emailContatto,
        nomeContatto: this.form.value.nomeContatto,
        cognomeContatto: this.form.value.cognomeContatto,
        telefonoContatto: this.form.value.telefonoContatto,
        tipoCliente: this.form.value.tipoCliente,
        sedeLegale: this.form.value.sedeLegale,
        sedeOperativa: this.form.value.sedeOperativa,
      };

      this.clientiService
        .registerClienti(formData, this.logoAziendaleFile)
        .subscribe(
          (res) => {
            this.router.navigate(['/clienti']);
            alert('Registrazione cliente effettuata correttamente');
          },
          (error) => {
            alert('Errore nella registrazione del cliente: ' + error.message);
          }
        );
    } else {
      alert('Controlla i tuoi dati, ci sono errori nel modulo.');
    }
  }
}
