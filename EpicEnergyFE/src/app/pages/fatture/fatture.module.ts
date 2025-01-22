import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FattureRoutingModule } from './fatture-routing.module';
import { FattureComponent } from './fatture.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [FattureComponent],
  imports: [CommonModule, FattureRoutingModule, ReactiveFormsModule],
})
export class FattureModule {}
