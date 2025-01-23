import { Component } from '@angular/core';
import { ClientiService } from '../../services/clienti.service';

@Component({
  selector: 'app-clienti',
  templateUrl: './clienti.component.html',
  styleUrl: './clienti.component.scss',
})
export class ClientiComponent {
  constructor(private clientiSvc: ClientiService) {}

  ngOnInit(): void {
    this.clientiSvc.getClienti(0).subscribe((clienti) => {
      console.log(clienti);
    });
  }
}
