import { Icon } from '@material-ui/core';
import React from 'react';
import { Spielerfarbe } from '../../model/Spieler';
import { useStyles } from './Spielfigur.styles';

export interface Props {
  farbe: Spielerfarbe;
  hervorheben: boolean;
  anklickbar: boolean;
}

function Spielfigur(props: Props): JSX.Element {
  const classes = useStyles(props);

  return <Icon className={classes.icon}>person</Icon>;
}

export default Spielfigur;
