import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent implements OnInit {
  name: string = 'Joseph';
  profileImg: string =
    'https://img.freepik.com/premium-vector/man-avatar-profile-picture-isolated-background-avatar-profile-picture-man_1293239-4866.jpg';
  fattureArr: any;
  clientiArr: any;

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
}
