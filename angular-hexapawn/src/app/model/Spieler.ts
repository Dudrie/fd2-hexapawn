export enum Spielerfarbe {
  ROT = 'Rot',
  BLAU = 'Blau',
}

export class Spieler {
  constructor(readonly spielerfarbe: Spielerfarbe) {}
}
