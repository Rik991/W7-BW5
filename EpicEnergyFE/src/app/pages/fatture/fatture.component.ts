import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';

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

  //grazie al @viewChild possiamo accedere al nostro elemento html e quindi creare un pdf
  downloadPDF() {
    const data = this.fatturaElement.nativeElement;
    html2canvas(data).then((canvas) => {
      const imgWidth = 208;
      const imgHeight = (canvas.height * imgWidth) / canvas.width;
      const contentDataURL = canvas.toDataURL('image/png');
      const pdf = new jsPDF('p', 'mm', 'a4');
      const position = 0;
      pdf.addImage(contentDataURL, 'PNG', 0, position, imgWidth, imgHeight);
      pdf.save('fattura.pdf');
    });
  }
}
