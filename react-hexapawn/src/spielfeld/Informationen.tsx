import React from 'react';
import { Typography } from '@material-ui/core';
import { useSelector } from 'react-redux';
import { Spielzustand } from '../store';

function Informationen(): JSX.Element {
  const aktuellerSpieler = useSelector((state: Spielzustand) => state.aktuellerSpieler);

  return <Typography>Aktueller Spieler: {aktuellerSpieler}</Typography>;
}

export default Informationen;
