import { BehaviorSubject } from 'rxjs';
import { Figur, Koordinaten } from '../model/Figur';
import { Kachel } from '../model/Kachel';
import { Spieler, Spielerfarbe } from '../model/Spieler';

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
    this.aktuellerSpieler$ = new BehaviorSubject<Spieler>(new Spieler(Spielerfarbe.BLAU));
    this.spieler = {
      [Spielerfarbe.BLAU]: new Spieler(Spielerfarbe.BLAU),
      [Spielerfarbe.ROT]: new Spieler(Spielerfarbe.ROT),
    };

    this.zuruecksetzen();
  }

  zuruecksetzen(): void {
    this.initKacheln();

    this.gewinner$.next(undefined);
    this.ausgewaehlteKachel$.next(undefined);
    this.aktuellerSpieler$.next(this.spieler[Spielerfarbe.BLAU]);
  }

  getFigurAnKoordinaten(koordinaten: Koordinaten): Figur | undefined {
    const kacheln = this.kacheln$.value;

    return kacheln.find(kachel => kachel.isBeiKoordinaten(koordinaten))?.figur;
  }

  bewegeAusgewaehlteFigurNach(endKachel: Kachel): void {
    const kacheln = this.kacheln$.value;
    const startKachel = this.ausgewaehlteKachel$.value;

    if (!startKachel?.figur) {
      throw new Error(
        'Es wurde keine Kachel mit einer Figur ausgewählt. Die Bewegung kann nicht durchgeführt werden.'
      );
    }

    if (!this.isValideBewegung(startKachel, endKachel)) {
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

    const isSpielende = this.isAktuellerSpielerGewinner(endKachel);

    if (isSpielende) {
      this.gewinner$.next(this.aktuellerSpieler$.value);
    } else {
      this.verwerfeKachelauswahl();
      this.aendereSpieler();
    }
  }

  waehleKachelAus(kachel: Kachel): void {
    if (!this.ausgewaehlteKachel$.value) {
      this.ausgewaehlteKachel$.next(kachel);
    }
  }

  verwerfeKachelauswahl(): void {
    if (this.ausgewaehlteKachel$.value) {
      this.ausgewaehlteKachel$.next(undefined);
    }
  }

  private isValideBewegung(startKachel: Kachel, endKachel: Kachel): boolean {
    if (!this.isKoordinatenImSpielfeld(endKachel.koordinaten)) {
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

  private aendereSpieler(): void {
    this.aktuellerSpieler$.next(this.getNaechsterSpieler());
  }

  private isKoordinatenImSpielfeld({ x, y }: Koordinaten): boolean {
    return x >= 0 && x <= 2 && y >= 0 && y <= 2;
  }

  private getNaechsterSpieler(): Spieler {
    if (this.aktuellerSpieler$.value.spielerfarbe === Spielerfarbe.BLAU) {
      return this.spieler[Spielerfarbe.ROT];
    } else {
      return this.spieler[Spielerfarbe.BLAU];
    }
  }

  private isAktuellerSpielerGewinner(endKachel: Kachel): boolean {
    const spieler = this.aktuellerSpieler$.value;
    const letzteReihe = 1 + spieler.richtung;
    const kacheln = this.kacheln$.value;

    // Der aktuelle Spieler hat die für ihn letzte Reihe erreicht und gewinnt.
    if (endKachel.koordinaten.y === letzteReihe) {
      return true;
    }

    // Der aktuelle Spieler ist der einzige mit Figuren auf dem Feld.
    const einzigerSpielerMitFiguren = kacheln.reduce((bisherNurEineFarbe, kachel) => {
      if (kachel.figur) {
        return bisherNurEineFarbe && kachel.figur.spielerfarbe === spieler.spielerfarbe;
      }

      return bisherNurEineFarbe;
    }, true);

    if (einzigerSpielerMitFiguren) {
      return true;
    }

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

    return moeglicheEndkacheln.length === 0;
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
