import { Box } from '@material-ui/core';
import React from 'react';
import Brett from '../components/brett/Brett';
import Infobox from '../components/infobox/Infobox';
import { SpielProvider } from '../spiel-service/Spiel.context';

function App(): JSX.Element {
  return (
    <SpielProvider>
      <Box display='flex' alignItems='center' justifyContent='center' height='100vh'>
        <Box display='flex'>
          <Brett />

          <Infobox />
        </Box>
      </Box>
    </SpielProvider>
  );
}

export default App;
