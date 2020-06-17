export enum Spielerfarbe {
  ROT = 'Rot',
  BLAU = 'Blau',
}

export class Spieler {
  constructor(readonly spielerfarbe: Spielerfarbe) {}

  get richtung(): number {
    return this.spielerfarbe === Spielerfarbe.BLAU ? -1 : 1;
  }
}
