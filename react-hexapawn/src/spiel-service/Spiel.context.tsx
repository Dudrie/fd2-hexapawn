import React, { useContext, PropsWithChildren, useRef } from 'react';
import { SpielService } from './spiel.service';

interface SpielContextValue {
  spielService: SpielService;
}

const SpielContext = React.createContext<SpielContextValue>({ spielService: new SpielService() });

export function useSpielService(): SpielService {
  const { spielService } = useContext(SpielContext);

  return spielService;
}

export function SpielProvider({ children }: PropsWithChildren<{}>): JSX.Element {
  const { current: spielService } = useRef(new SpielService());

  return <SpielContext.Provider value={{ spielService }}>{children}</SpielContext.Provider>;
}
