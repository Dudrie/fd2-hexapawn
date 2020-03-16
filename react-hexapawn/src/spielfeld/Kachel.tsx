import { Box } from '@material-ui/core';
import React from 'react';
import { useSelector } from 'react-redux';
import { Figur as FigurZustand, Position, Spielzustand } from '../store';
import Figur from './Figur';

export type KachelClickListener = (position: Position, figur?: FigurZustand) => void;

interface Props {
  zeile: number;
  spalte: number;
  onClick: KachelClickListener;
}

function Kachel({ zeile, spalte, onClick }: Props): JSX.Element {
  const figur = useSelector((state: Spielzustand) =>
    state.figuren.find(fig => {
      const { position } = fig;

      return position.zeile === zeile && position.spalte === spalte;
    })
  );

  return (
    <Box
      border='1px solid #000'
      width={64}
      height={64}
      display='flex'
      justifyContent='center'
      alignItems='center'
      onClick={() => onClick({ zeile, spalte }, figur)}
    >
      {figur && <Figur farbe={figur.spieler} />}
    </Box>
  );
}

export default Kachel;
