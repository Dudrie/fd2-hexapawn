import { Injectable } from '@angular/core';
import { Figur, Koordinaten } from '../model/Figur';
import { Spielerfarbe } from '../model/Spieler';

@Injectable({
  providedIn: 'root',
})
export class SpielService {
  private figuren: Figur[] = [];

  constructor() {
    this.init();
  }

  getFigurAnKoordinaten(koordinaten: Koordinaten): Figur | undefined {
    return this.figuren.find((figur) => figur.isBeiKoordinaten(koordinaten));
  }

  private init(): void {
    this.figuren = [];

    for (let x = 0; x < 3; x++) {
      this.figuren.push(new Figur({ x, y: 0 }, Spielerfarbe.ROT));
    }

    for (let x = 0; x < 3; x++) {
      this.figuren.push(new Figur({ x, y: 2 }, Spielerfarbe.BLAU));
    }
  }
}
