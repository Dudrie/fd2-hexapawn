import React from 'react';
import { SpielProvider } from '../spiel-service/Spiel.context';
import Brett from '../components/brett/Brett';
import { Box } from '@material-ui/core';
import Infobox from '../components/infobox/Infobox';

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
