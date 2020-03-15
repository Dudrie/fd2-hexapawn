import React from 'react';
import { Box } from '@material-ui/core';
import Figur from './Figur';
import { Spielerfarbe } from '../store/Spielerfarbe';

interface Props {
  zeile: number;
  spalte: number;
}

function Kachel({ zeile, spalte }: Props): JSX.Element {
  return (
    <Box
      border='1px solid #000'
      width={64}
      height={64}
      display='flex'
      justifyContent='center'
      alignItems='center'
    >
      <Figur farbe={Math.random() < 0.5 ? Spielerfarbe.BLAU : Spielerfarbe.ROT} />
    </Box>
  );
}

export default Kachel;
