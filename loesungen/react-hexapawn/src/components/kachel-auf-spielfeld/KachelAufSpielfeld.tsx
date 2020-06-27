import { Box } from '@material-ui/core';
import React from 'react';
import useObservable from '../../helpers/useObservable';
import { Kachel } from '../../model/Kachel';
import { useSpielService } from '../../spiel-service/Spiel.context';
import Spielfigur from '../spielfigur/Spielfigur.component';

interface Props {
  kachel: Kachel;
}

function KachelAufSpielfeld({ kachel }: Props): JSX.Element {
  const spielService = useSpielService();
  const ausgewaehlteKachel = useObservable(spielService.ausgewaehlteKachel$);
  const aktuellerSpieler = useObservable(spielService.aktuellerSpieler$);
  const gewinner = useObservable(spielService.gewinner$);

  const waehleKachelAus = (kachelZumAuswaehlen: Kachel) => {
    if (
      kachelZumAuswaehlen.figur &&
      kachelZumAuswaehlen.figur.spielerfarbe === aktuellerSpieler?.spielerfarbe
    ) {
      spielService.waehleKachelAus(kachelZumAuswaehlen);
    }
  };

  const fuehreZugAus = (zielKachel: Kachel) => {
    spielService.bewegeAusgewaehlteFigurNach(zielKachel);
  };

  const handleKachelClick = () => {
    if (!!gewinner) {
      return;
    }

    if (!!ausgewaehlteKachel) {
      if (kachel === ausgewaehlteKachel) {
        spielService.verwerfeKachelauswahl();
      } else {
        fuehreZugAus(kachel);
      }
    } else {
      waehleKachelAus(kachel);
    }
  };

  return (
    <Box
      display='flex'
      height='100%'
      alignItems='center'
      justifyContent='center'
      border='1px solid #000'
      onClick={handleKachelClick}
    >
      {kachel.figur && (
        <Spielfigur
          farbe={kachel.figur?.spielerfarbe}
          hervorheben={kachel === ausgewaehlteKachel}
          anklickbar={
            !gewinner &&
            !!aktuellerSpieler &&
            kachel.figur?.spielerfarbe === aktuellerSpieler.spielerfarbe
          }
        />
      )}
    </Box>
  );
}

export default KachelAufSpielfeld;
