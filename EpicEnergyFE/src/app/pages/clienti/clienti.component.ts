import { Component } from '@angular/core';
import { ClientiService } from '../../services/clienti.service';
import { iPageClienti } from '../../interfaces/i-page-clienti';
import { iCliente } from '../../interfaces/i-clienti';

@Component({
  selector: 'app-clienti',
  templateUrl: './clienti.component.html',
  styleUrl: './clienti.component.scss',
})
export class ClientiComponent {
  constructor(private clientiSvc: ClientiService) {}

  pageClienti: iPageClienti[] = [];
  clientiArray: iCliente[] = [];

  ngOnInit(): void {
    this.clientiSvc.getClienti(0).subscribe((clienti) => {
      console.log(clienti);
      this.clientiArray = clienti.content;
    });
  }
}
