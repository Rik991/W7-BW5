import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientiRoutingModule } from './clienti-routing.module';
import { ClientiComponent } from './clienti.component';
import { RegisterClientiComponent } from './register-clienti/register-clienti.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [ClientiComponent, RegisterClientiComponent],
  imports: [CommonModule, ReactiveFormsModule, ClientiRoutingModule],
})
export class ClientiModule {}
