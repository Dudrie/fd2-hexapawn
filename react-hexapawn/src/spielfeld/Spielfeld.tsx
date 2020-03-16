import { Box } from '@material-ui/core';
import { createStyles, makeStyles } from '@material-ui/core/styles';
import React, { useState } from 'react';
import Informationen from './Informationen';
import Kachel, { KachelClickListener } from './Kachel';
import { Position, Figur, Spielzustand } from '../store';
import { useSelector } from 'react-redux';

const useStyles = makeStyles(theme =>
  createStyles({
    '@global': {
      body: {
        maxWidth: '100vw',
        maxHeight: '100vh',
        // overflowY: 'hidden',
      },
    },
    zeile: {
      '&:after': {
        clear: 'both',
        content: '""',
      },
    },
  })
);

function Spielfeld(): JSX.Element {
  const classes = useStyles();
  const [ausgewahlteFigur, setAusgewahlteFigur] = useState<Figur>();
  const aktuellerSpieler = useSelector((state: Spielzustand) => state.aktuellerSpieler);

  function generiereKacheln(): JSX.Element[] {
    const kacheln: JSX.Element[] = [];

    for (let spalte = 0; spalte < 3; spalte++) {
      const kachelZeile: JSX.Element[] = [];

      for (let zeile = 0; zeile < 3; zeile++) {
        kachelZeile.push(
          <Kachel
            key={`${spalte}${zeile}`}
            zeile={zeile}
            spalte={spalte}
            onClick={onKachelClicked}
          />
        );
      }

      kacheln.push(
        <div key={`${spalte}`} className={classes.zeile}>
          {kachelZeile}
        </div>
      );
    }

    return kacheln;
  }

  function onKachelClicked(pos: Position, figur?: Figur) {
    if (!ausgewahlteFigur && !!figur && figur.spieler === aktuellerSpieler) {
      setAusgewahlteFigur(figur);
    }

    if (!!ausgewahlteFigur && !figur) {
      setAusgewahlteFigur(undefined);
    }
  }

  return (
    <Box display='flex' alignItems='center' justifyContent='center' height='100vh'>
      <Box display='flex'>{generiereKacheln()}</Box>

      <Box marginLeft={4}>
        <Informationen />
      </Box>
    </Box>
  );
}

export default Spielfeld;
