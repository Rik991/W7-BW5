import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientiRoutingModule } from './clienti-routing.module';
import { ClientiComponent } from './clienti.component';
import { RegisterClientiComponent } from './register-clienti/register-clienti.component';
import { ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from '../../shared/shared.module';

@NgModule({
  declarations: [ClientiComponent, RegisterClientiComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    ClientiRoutingModule,
    SharedModule,
  ],
})
export class ClientiModule {}
