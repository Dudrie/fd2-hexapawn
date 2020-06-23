import { Box } from '@material-ui/core';
import React from 'react';
import { SpielProvider } from '../spiel-service/Spiel.context';

function App(): JSX.Element {
  return (
    <SpielProvider>
      <Box display='flex' alignItems='center' justifyContent='center' height='100vh'>
        Hier kommt später das Spielfeld hin.
      </Box>
    </SpielProvider>
  );
}

export default App;
