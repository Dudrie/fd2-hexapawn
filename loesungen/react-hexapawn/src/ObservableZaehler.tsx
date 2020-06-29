import React from 'react';
import { BehaviorSubject } from 'rxjs';
import useObservable from './helpers/useObservable';
import { Box, Button, Typography } from '@material-ui/core';

const zaehler$ = new BehaviorSubject<number>(0);

function ObservableZaehler(): JSX.Element {
  const aktuelleZahl = useObservable(zaehler$) ?? 0;

  return (
    <Box display='flex' flexDirection='column'>
      <Button variant='contained' onClick={() => zaehler$.next(aktuelleZahl + 1)}>
        Erhöhen
      </Button>

      <Typography>Aktuell: {aktuelleZahl}</Typography>

      <Button variant='contained' onClick={() => zaehler$.next(aktuelleZahl - 1)}>
        Verringern
      </Button>
    </Box>
  );
}

export default ObservableZaehler;
