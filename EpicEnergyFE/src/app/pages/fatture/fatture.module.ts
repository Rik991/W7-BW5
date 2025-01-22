import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FattureRoutingModule } from './fatture-routing.module';
import { FattureComponent } from './fatture.component';
import { ReactiveFormsModule } from '@angular/forms';
import { NewFatturaComponent } from './new-fattura/new-fattura.component';

@NgModule({
  declarations: [FattureComponent, NewFatturaComponent],
  imports: [CommonModule, FattureRoutingModule, ReactiveFormsModule],
})
export class FattureModule {}
