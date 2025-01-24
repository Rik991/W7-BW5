import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientiComponent } from './clienti.component';
import { RegisterClientiComponent } from './register-clienti/register-clienti.component';

const routes: Routes = [
  { path: '', component: ClientiComponent },
  { path: 'register', component: RegisterClientiComponent },
  { path: 'register/:id', component: RegisterClientiComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ClientiRoutingModule {}
