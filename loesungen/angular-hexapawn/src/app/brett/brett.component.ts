import { Component, OnInit } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Kachel } from '../model/Kachel';
import { Spieler } from '../model/Spieler';
import { SpielService } from '../spiel-service/spiel.service';

@Component({
  selector: 'app-brett',
  templateUrl: './brett.component.html',
  styleUrls: ['./brett.component.scss'],
})
export class BrettComponent implements OnInit {
  readonly kacheln$: BehaviorSubject<Kachel[]>;
  gewinner?: Spieler;
  aktuellerSpieler!: Spieler;

  private ausgewaehlteKachel: Kachel | undefined;

  constructor(private spielService: SpielService) {
    this.kacheln$ = this.spielService.kacheln$;
  }

  ngOnInit(): void {
    this.spielService.ausgewaehlteKachel$.subscribe(kachel => (this.ausgewaehlteKachel = kachel));
    this.spielService.aktuellerSpieler$.subscribe(spieler => (this.aktuellerSpieler = spieler));
    this.spielService.gewinner$.subscribe(gewinner => (this.gewinner = gewinner));
  }

  istKachelAusgewaehlt(kachel: Kachel): boolean {
    if (!kachel.figur) {
      return false;
    }

    return this.ausgewaehlteKachel === kachel;
  }

  istKachelAnklickbar(kachel: Kachel): boolean {
    return this.aktuellerSpieler.spielerfarbe === kachel.figur?.spielerfarbe;
  }

  kachelClick(kachel: Kachel) {
    if (!!this.gewinner) {
      return;
    }

    if (this.ausgewaehlteKachel) {
      if (kachel === this.ausgewaehlteKachel) {
        this.spielService.verwerfeKachelauswahl();
      } else if (this.spielService.isValideBewegung(this.ausgewaehlteKachel, kachel)) {
        this.fuehreZugAus(kachel);
      }
    } else {
      this.waehleKachel(kachel);
    }
  }

  private fuehreZugAus(zielKachel: Kachel) {
    this.spielService.bewegeAusgewaehlteFigurNach(zielKachel);
    this.spielService.verwerfeKachelauswahl();
    this.spielService.aendereSpieler();
  }

  private waehleKachel(kachel: Kachel) {
    if (kachel.figur && kachel.figur.spielerfarbe === this.aktuellerSpieler.spielerfarbe) {
      this.spielService.waehleKachelAus(kachel);
    }
  }
}
