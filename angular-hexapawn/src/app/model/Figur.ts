import { Spielerfarbe } from './Spieler';

export interface Koordinaten {
  readonly x: number;
  readonly y: number;
}

export class Figur {
  constructor(private koordinaten: Koordinaten, public readonly spielerfarbe: Spielerfarbe) {}

  isBeiKoordinaten(koordinaten: Koordinaten): boolean {
    const { x, y } = this.koordinaten;
    return koordinaten.x === x && koordinaten.y === y;
  }
}
