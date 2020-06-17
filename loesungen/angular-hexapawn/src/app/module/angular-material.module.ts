import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';

@NgModule({
  imports: [MatButtonModule, MatGridListModule, MatIconModule],
  exports: [MatButtonModule, MatGridListModule, MatIconModule],
})
export class AngularMaterialModule {}
