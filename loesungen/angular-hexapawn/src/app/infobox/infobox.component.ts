import { Component, OnInit } from '@angular/core';
import { SpielService } from '../spiel-service/spiel.service';
import { Spieler } from '../model/Spieler';

@Component({
  selector: 'app-infobox',
  templateUrl: './infobox.component.html',
  styleUrls: ['./infobox.component.scss'],
})
export class InfoboxComponent implements OnInit {
  gewinner?: Spieler;
  aktuellerSpieler: Spieler;

  wurdeNeustartAngeklickt: boolean;

  constructor(private spielService: SpielService) {
    this.aktuellerSpieler = this.spielService.aktuellerSpieler$.value;
    this.wurdeNeustartAngeklickt = false;
  }

  ngOnInit(): void {
    this.spielService.gewinner$.subscribe(gewinner => (this.gewinner = gewinner));
    this.spielService.aktuellerSpieler$.subscribe(spieler => (this.aktuellerSpieler = spieler));
  }

  neustartClicked() {
    if (this.wurdeNeustartAngeklickt) {
      this.spielService.zuruecksetzen();
      this.wurdeNeustartAngeklickt = false;
    } else {
      this.wurdeNeustartAngeklickt = true;
      setTimeout(() => {
        this.wurdeNeustartAngeklickt = false;
      }, 2000);
    }
  }
}
