import { Spielerfarbe, Spieler } from './Spieler';

export interface Koordinaten {
  readonly x: number;
  readonly y: number;
}

export class Figur {
  constructor(readonly id: number, readonly koordinaten: Koordinaten, readonly spieler: Spieler) {}

  get spielerfarbe(): Spielerfarbe {
    return this.spieler.spielerfarbe;
  }
}
