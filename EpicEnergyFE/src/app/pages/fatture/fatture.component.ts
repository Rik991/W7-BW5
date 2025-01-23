import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-fatture',
  templateUrl: './fatture.component.html',
  styleUrl: './fatture.component.scss',
})
export class FattureComponent implements OnInit {
  form!: FormGroup;

  @ViewChild('fattura', { static: false }) fatturaElement!: ElementRef;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      ragioneSociale: [''],
      importoFattura: [''],
      dataFatturazione: [''],
      statoFattura: [''],
    });
  }
}
