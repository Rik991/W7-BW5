import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-fatture',
  templateUrl: './fatture.component.html',
  styleUrl: './fatture.component.scss',
})
export class FattureComponent implements OnInit {
  form!: FormGroup;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      ragioneSociale: [''],
      importoFattura: [''],
      dataFatturazione: [''],
      statoFattura: [''],
    });
  }

  onSubmit() {
    console.log(this.form.value);
  }
}
