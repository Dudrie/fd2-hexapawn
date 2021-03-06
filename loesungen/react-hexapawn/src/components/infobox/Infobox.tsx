import { Box, Button, Typography } from '@material-ui/core';
import { createStyles, makeStyles } from '@material-ui/core/styles';
import React, { useState } from 'react';
import useObservable from '../../helpers/useObservable';
import { useSpielService } from '../../spiel-service/Spiel.context';

const useStyles = makeStyles(() =>
  createStyles({
    restartButton: {
      marginTop: 'auto',
    },
  })
);

function Infobox(): JSX.Element {
  const classes = useStyles();

  const spielService = useSpielService();
  const gewinner = useObservable(spielService.gewinner$);
  const aktuellerSpieler = useObservable(spielService.aktuellerSpieler$);

  const [neustartTimer, setNeustartTimer] = useState<NodeJS.Timeout>();
  const isNeustartAngeklickt = neustartTimer !== undefined;

  const handleNeustartClicked = () => {
    if (neustartTimer) {
      spielService.zuruecksetzen();
      clearTimeout(neustartTimer);
      setNeustartTimer(undefined);
    } else {
      const timer = setTimeout(() => setNeustartTimer(undefined), 2000);
      setNeustartTimer(timer);
    }
  };

  return (
    <Box display='flex' flexDirection='column' width={250} marginLeft={3}>
      <Typography variant='h6'>
        {!!gewinner
          ? `Spieler ${gewinner.spielerfarbe} gewinnt`
          : `Aktueller Spieler: ${aktuellerSpieler?.spielerfarbe}`}
      </Typography>

      <Button
        variant='contained'
        color='primary'
        className={classes.restartButton}
        onClick={handleNeustartClicked}
      >
        {!isNeustartAngeklickt ? 'Neustart' : 'Wirklich neustarten?'}
      </Button>
    </Box>
  );
}

export default Infobox;
