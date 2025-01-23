import { ClientiService } from './../../services/clienti.service';
import { Component, OnInit } from '@angular/core';
import { iFilterClienti } from '../../interfaces/i-filter-clienti';
import { AuthService } from '../../auth/auth.service';
import { iUser } from '../../interfaces/i-user';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent implements OnInit {
  user!: iUser | undefined;
  name: string | undefined = '';
  profileImg: string =
    'https://img.freepik.com/premium-vector/man-avatar-profile-picture-isolated-background-avatar-profile-picture-man_1293239-4866.jpg';

  constructor(
    private clientiSvc: ClientiService,
    private authSvc: AuthService
  ) {}

  ngOnInit(): void {
    this.authSvc.user$.subscribe((user) => (this.user = user));

    this.name = this.user?.nome;
  }

  data: iFilterClienti = {
    key: 1,
    ragioneSociale: '',
    dataIniziale: '',
    dataFinale: '',
    fatturatoMin: '',
    fatturatoMax: '',
  };

  sendData(key: number) {
    this.data.key = key;
    this.clientiSvc.sendData(this.data);
  }
}
