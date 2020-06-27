import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AngularMaterialModule } from './module/angular-material.module';
import { AppComponent } from './app.component';
import { SpielfeldComponent } from './spielfeld/spielfeld.component';
import { KachelComponent } from './kachel/kachel.component';
import { SpielfigurComponent } from './spielfigur/spielfigur.component';
import { InfoboxComponent } from './infobox/infobox.component';

@NgModule({
  declarations: [
    AppComponent,
    SpielfeldComponent,
    KachelComponent,
    SpielfigurComponent,
    InfoboxComponent,
  ],
  imports: [BrowserModule, BrowserAnimationsModule, AngularMaterialModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
