import { Figur, Koordinaten } from './Figur';

export class Kachel {
  constructor(readonly koordinaten: Koordinaten, readonly figur?: Figur) {}

  isBeiKoordinaten(koordinaten: Koordinaten): boolean {
    const { x, y } = this.koordinaten;
    return koordinaten.x === x && koordinaten.y === y;
  }
}
