import { Box } from '@material-ui/core';
import { createStyles, makeStyles } from '@material-ui/core/styles';
import React from 'react';
import Informationen from './Informationen';
import Kachel from './Kachel';

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

function generiereKacheln(rowClass: string): JSX.Element[] {
  const kacheln: JSX.Element[] = [];

  for (let spalte = 0; spalte < 3; spalte++) {
    const kachelZeile: JSX.Element[] = [];

    for (let zeile = 0; zeile < 3; zeile++) {
      kachelZeile.push(<Kachel key={`${spalte}${zeile}`} zeile={zeile} spalte={spalte} />);
    }

    kacheln.push(<div className={rowClass}>{kachelZeile}</div>);
  }

  return kacheln;
}

function Spielfeld(): JSX.Element {
  const classes = useStyles();

  return (
    <Box display='flex' alignItems='center' justifyContent='center' height='100vh'>
      <Box display='flex'>{generiereKacheln(classes.zeile)}</Box>

      <Box marginLeft={4}>
        <Informationen />
      </Box>
    </Box>
  );
}

export default Spielfeld;
