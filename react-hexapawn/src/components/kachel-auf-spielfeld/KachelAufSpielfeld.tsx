import React from 'react';
import { Kachel } from '../../model/Kachel';
import { Box } from '@material-ui/core';
import Spielfigur from '../spielfigur/Spielfigur.component';

interface Props {
  kachel: Kachel;
  hervorheben: boolean;
  anklickbar: boolean;
}

function KachelAufSpielfeld({ kachel, ...props }: Props): JSX.Element {
  return (
    <Box
      display='flex'
      height='100%'
      alignItems='center'
      justifyContent='center'
      border='1px solid #000'
    >
      {kachel.figur && <Spielfigur farbe={kachel.figur?.spielerfarbe} {...props} />}
    </Box>
  );
}

export default KachelAufSpielfeld;
