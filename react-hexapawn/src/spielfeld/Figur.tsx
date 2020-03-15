import React from 'react';
import { Spielerfarbe } from '../store/Spielerfarbe';
import { Person as SpielerIcon } from '@material-ui/icons';

interface Props {
  farbe: Spielerfarbe;
}

function Figur({ farbe }: Props): JSX.Element {
  return (
    <SpielerIcon
      style={{ fontSize: 48 }}
      color={farbe === Spielerfarbe.BLAU ? 'primary' : 'secondary'}
    />
  );
}

export default Figur;
