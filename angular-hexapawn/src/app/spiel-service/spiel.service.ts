import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Figur, Koordinaten } from '../model/Figur';
import { Spieler, Spielerfarbe } from '../model/Spieler';
import { Kachel } from '../model/Kachel';

@Injectable({
  providedIn: 'root',
})
export class SpielService {
  readonly kacheln$: BehaviorSubject<Kachel[]>;
  readonly ausgewaehlteKachel$: BehaviorSubject<Kachel | undefined>;
  readonly aktuellerSpieler$: BehaviorSubject<Spieler>;
  readonly gewinner$: BehaviorSubject<Spieler | undefined>;

  private spieler: { [key in Spielerfarbe]: Spieler };

  constructor() {
    this.kacheln$ = new BehaviorSubject<Kachel[]>([]);
    this.ausgewaehlteKachel$ = new BehaviorSubject<Kachel | undefined>(undefined);
    this.gewinner$ = new BehaviorSubject<Spieler | undefined>(undefined);
    this.spieler = {
      [Spielerfarbe.BLAU]: new Spieler(Spielerfarbe.BLAU),
      [Spielerfarbe.ROT]: new Spieler(Spielerfarbe.ROT),
    };

    this.aktuellerSpieler$ = new BehaviorSubject<Spieler>(this.spieler[Spielerfarbe.BLAU]);
    this.initKacheln();
  }

  getFigurAnKoordinaten(koordinaten: Koordinaten): Figur | undefined {
    const kacheln = this.kacheln$.value;

    return kacheln.find(kachel => kachel.isBeiKoordinaten(koordinaten))?.figur;
  }

  bewegeAusgewaehlteFigurNach(endKachel: Kachel) {
    const kacheln = this.kacheln$.value;
    const startKachel = this.ausgewaehlteKachel$.value;

    if (!startKachel?.figur) {
      // TODO: Error?!
      return;
    }

    const figur = startKachel.figur;

    this.kacheln$.next(
      kacheln.map(kachel => {
        if (kachel === endKachel) {
          return new Kachel(kachel.koordinaten, figur);
        }

        if (kachel === startKachel) {
          return new Kachel(startKachel.koordinaten, undefined);
        }

        return kachel;
      })
    );

    this.pruefeAufSpielende(endKachel);
  }

  waehleKachelAus(kachel: Kachel) {
    if (!this.ausgewaehlteKachel$.value) {
      this.ausgewaehlteKachel$.next(kachel);
    }
  }

  verwerfeKachelauswahl() {
    if (this.ausgewaehlteKachel$.value) {
      this.ausgewaehlteKachel$.next(undefined);
    }
  }

  isValideBewegung(startKachel: Kachel, endKachel: Kachel): boolean {
    if (!this.istKoordinatenImSpielfeld(endKachel.koordinaten)) {
      return false;
    }

    if (!startKachel.figur) {
      return false;
    }

    const dx = endKachel.koordinaten.x - startKachel.koordinaten.x;
    const dy = endKachel.koordinaten.y - startKachel.koordinaten.y;
    const spieler = this.spieler[startKachel.figur.spielerfarbe];

    if (!endKachel.figur) {
      return dx === 0 && dy === spieler.richtung;
    }

    if (endKachel.figur.spielerfarbe === startKachel.figur?.spielerfarbe) {
      return false;
    }

    return Math.abs(dx) === 1 && dy === spieler.richtung;
  }

  aendereSpieler() {
    this.aktuellerSpieler$.next(this.getNaechsterSpieler());
  }

  private istKoordinatenImSpielfeld({ x, y }: Koordinaten): boolean {
    return x >= 0 && x <= 2 && y >= 0 && y <= 2;
  }

  private getNaechsterSpieler(): Spieler {
    if (this.aktuellerSpieler$.value.spielerfarbe === Spielerfarbe.BLAU) {
      return this.spieler[Spielerfarbe.ROT];
    } else {
      return this.spieler[Spielerfarbe.BLAU];
    }
  }

  private pruefeAufSpielende(endKachel: Kachel) {
    const spieler = this.aktuellerSpieler$.value;
    const letzteReihe = 1 + spieler.richtung;
    const kacheln = this.kacheln$.value;

    // Der aktuelle Spieler hat die für ihn letzte Reihe erreicht und gewinnt.
    if (endKachel.koordinaten.y === letzteReihe) {
      this.gewinner$.next(spieler);
      return;
    }

    // Der aktuelle Spieler ist der einzige mit Figuren auf dem Feld.
    kacheln.reduce((bisherNurEineFarbe, kachel) => {
      if (kachel.figur) {
        return bisherNurEineFarbe && kachel.figur.spielerfarbe === spieler.spielerfarbe;
      }

      return bisherNurEineFarbe;
    }, true);

    // Der Gegner kann keinen Zug mehr durchführen.
    const gegner = this.getNaechsterSpieler();
    const gegnerischeKacheln: Kachel[] = kacheln.filter(
      kachel => kachel.figur?.spielerfarbe === gegner.spielerfarbe
    );
    const moeglicheEndkacheln: Kachel[] = [];

    gegnerischeKacheln.forEach(kachel => {
      const endkacheln = this.berechneAlleValidenBewegungen(kachel);
      moeglicheEndkacheln.push(...endkacheln);
    });

    if (moeglicheEndkacheln.length === 0) {
      this.gewinner$.next(spieler);
    }
  }

  private berechneAlleValidenBewegungen(startKachel: Kachel): Kachel[] {
    if (!startKachel.figur) {
      return [];
    }

    const spieler = this.spieler[startKachel.figur.spielerfarbe];
    const y = startKachel.koordinaten.y + spieler.richtung;

    const kacheln: Kachel[] = [];

    for (let x = 0; x < 3; x++) {
      const kachel = this.kacheln$.value.find(k => k.isBeiKoordinaten({ x, y }));
      if (kachel && this.isValideBewegung(startKachel, kachel)) {
        kacheln.push(kachel);
      }
    }

    return kacheln;
  }

  private initKacheln() {
    const { [Spielerfarbe.BLAU]: spielerBlau, [Spielerfarbe.ROT]: spielerRot } = this.spieler;
    const kacheln: Kachel[] = new Array(9).fill(0).map((_, idx) => {
      const koordinaten = this.indexAlsKoordinaten(idx);

      switch (koordinaten.y) {
        case 0:
          return new Kachel(koordinaten, new Figur(idx, koordinaten, spielerRot));
        case 2:
          return new Kachel(koordinaten, new Figur(idx, koordinaten, spielerBlau));
        default:
          return new Kachel(koordinaten);
      }
    });

    this.kacheln$.next(kacheln);
  }

  private indexAlsKoordinaten(index: number): Koordinaten {
    return {
      x: index % 3,
      y: Math.floor(index / 3),
    };
  }
}
