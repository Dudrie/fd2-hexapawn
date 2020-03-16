import { createStore } from 'redux';
import { Spielaktion } from './aktionen';
import { Spielerfarbe } from './Spielerfarbe';

export interface Position {
  readonly zeile: number;
  readonly spalte: number;
}

export interface Figur {
  readonly id: string;
  readonly position: Position;
  readonly spieler: Spielerfarbe;
}

export interface Spielzustand {
  readonly figuren: readonly Figur[];
  readonly aktuellerSpieler: Spielerfarbe;
}

const INITIALER_ZUSTAND: Spielzustand = {
  figuren: [
    { id: '0', position: { zeile: 0, spalte: 0 }, spieler: Spielerfarbe.ROT },
    { id: '1', position: { zeile: 0, spalte: 1 }, spieler: Spielerfarbe.ROT },
    { id: '2', position: { zeile: 0, spalte: 2 }, spieler: Spielerfarbe.ROT },
    { id: '3', position: { zeile: 2, spalte: 0 }, spieler: Spielerfarbe.BLAU },
    { id: '4', position: { zeile: 2, spalte: 1 }, spieler: Spielerfarbe.BLAU },
    { id: '5', position: { zeile: 2, spalte: 2 }, spieler: Spielerfarbe.BLAU },
  ],
  aktuellerSpieler: Spielerfarbe.BLAU,
};

function rootReducer(zustand: Spielzustand | undefined, aktion: Spielaktion): Spielzustand {
  if (!zustand) {
    return INITIALER_ZUSTAND;
  }

  return zustand;
}

export type RootState = ReturnType<typeof rootReducer>;
export const store = createStore(
  rootReducer,
  INITIALER_ZUSTAND,
  window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
);
