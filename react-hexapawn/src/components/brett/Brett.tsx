import React from 'react';
import { useSpielService } from '../../spiel-service/Spiel.context';
import useObservable from '../../helpers/useObservable';
import { Box } from '@material-ui/core';
import { Kachel } from '../../model/Kachel';
import KachelAufSpielfeld from '../kachel-auf-spielfeld/KachelAufSpielfeld';

function Brett(): JSX.Element {
  const spielService = useSpielService();
  const kacheln = useObservable(spielService.kacheln$);
  const aktuellerSpieler = useObservable(spielService.aktuellerSpieler$);

  return (
    <Box
      width={300}
      height={300}
      display='grid'
      gridTemplateColumns='repeat(3, 1fr)'
      gridTemplateRows='repeat(3, 1fr)'
    >
      {kacheln?.map((kachel, idx) => (
        <KachelAufSpielfeld key={idx} kachel={kachel} anklickbar={false} hervorheben={false} />
      ))}
    </Box>
  );
}

export default Brett;
