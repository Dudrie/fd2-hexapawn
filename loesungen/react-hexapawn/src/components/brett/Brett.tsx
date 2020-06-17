import { Box } from '@material-ui/core';
import React from 'react';
import useObservable from '../../helpers/useObservable';
import { useSpielService } from '../../spiel-service/Spiel.context';
import KachelAufSpielfeld from '../kachel-auf-spielfeld/KachelAufSpielfeld';

function Brett(): JSX.Element {
  const spielService = useSpielService();
  const kacheln = useObservable(spielService.kacheln$);

  return (
    <Box
      width={300}
      height={300}
      display='grid'
      gridTemplateColumns='repeat(3, 1fr)'
      gridTemplateRows='repeat(3, 1fr)'
    >
      {kacheln?.map((kachel, idx) => (
        <KachelAufSpielfeld key={idx} kachel={kachel} />
      ))}
    </Box>
  );
}

export default Brett;
