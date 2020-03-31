import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AngularMaterialModule } from './module/angular-material.module';
import { AppRoutingModule } from './module/app-routing.module';
import { AppComponent } from './app.component';
import { BrettComponent } from './brett/brett.component';
import { KachelComponent } from './kachel/kachel.component';
import { SpielfigurComponent } from './spielfigur/spielfigur.component';

@NgModule({
  declarations: [AppComponent, BrettComponent, KachelComponent, SpielfigurComponent],
  imports: [BrowserModule, AppRoutingModule, BrowserAnimationsModule, AngularMaterialModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
