import { makeStyles, Theme, createStyles } from '@material-ui/core';
import { Spielerfarbe } from '../../model/Spieler';
import { Props } from './Spielfigur.component';

export const useStyles = makeStyles<Theme, Props>(() =>
  createStyles({
    icon: props => {
      let color: string;
      if (props.hervorheben) {
        color = 'orange';
      } else {
        color = props.farbe === Spielerfarbe.BLAU ? 'blue' : 'red';
      }

      return { color, cursor: props.anklickbar ? 'pointer' : 'default', fontSize: 64 };
    },
  })
);
