import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientiService } from '../../../services/clienti.service';
import { iCliente } from '../../../interfaces/i-clienti';

@Component({
  selector: 'app-register-clienti',
  templateUrl: './register-clienti.component.html',
  styleUrls: ['./register-clienti.component.scss'],
})
export class RegisterClientiComponent implements OnInit {
  form!: FormGroup;
  logoAziendaleFile: File | undefined;
  id?: string;

  constructor(
    private fb: FormBuilder,
    private clientiService: ClientiService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id')!;

    this.form = this.fb.group({
      ragioneSociale: ['', Validators.required],
      partitaIva: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      dataUltimoContatto: [''],
      fatturatoAnnuale: ['', Validators.required],
      pec: ['', Validators.email],
      telefono: ['', Validators.required],
      emailContatto: [''],
      nomeContatto: [''],
      cognomeContatto: [''],
      telefonoContatto: [''],
      tipoCliente: ['', Validators.required],
      sedeLegale: this.fb.group({
        via: [''],
        civico: [''],
        localita: [''],
        cap: [''],
        comune: ['', Validators.required],
      }),
      sedeOperativa: this.fb.group({
        via: [''],
        civico: [''],
        localita: [''],
        cap: [''],
        comune: ['', Validators.required],
      }),
    });

    if (this.id) {
      this.clientiService
        .getClientiById(parseInt(this.id))
        .subscribe((cliente) => {
          this.form.patchValue(cliente);
          this.form
            .get('sedeLegale')
            ?.get('comune')
            ?.setValue(cliente.sedeLegale.comune);
          this.form
            .get('sedeOperativa')
            ?.get('comune')
            ?.setValue(cliente.sedeOperativa.comune);
        });
    }
  }

  onFileChange(event: any) {
    if (event.target.files.length > 0) {
      this.logoAziendaleFile = event.target.files[0];
    }
  }

  registerClienti() {
    if (this.form.valid) {
      const formData: Partial<iCliente> = {
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
        .subscribe({
          next: (res) => {
            this.router.navigate(['/clienti']);
            alert('Registrazione cliente effettuata correttamente');
          },
          error: (error) => {
            alert('Errore nella registrazione del cliente: ' + error.message);
          },
        });
    } else {
      alert('Controlla i tuoi dati, ci sono errori nel modulo.');
    }
  }
}
