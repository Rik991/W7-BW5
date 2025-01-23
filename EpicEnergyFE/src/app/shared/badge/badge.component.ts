import { Component, Input, input, OnInit } from '@angular/core';
import { iCliente } from '../../interfaces/i-clienti';

@Component({
  selector: 'app-badge',
  templateUrl: './badge.component.html',
  styleUrl: './badge.component.scss',
})
export class BadgeComponent implements OnInit {
  // da fare
  @Input() cliente!: iCliente;

  ngOnInit(): void {
    console.log('card', this.cliente);
  }
}
