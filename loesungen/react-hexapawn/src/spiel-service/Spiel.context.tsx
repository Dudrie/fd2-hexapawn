import React, { ReactNode, useContext, useRef } from 'react';
import { SpielService } from './Spiel.service';

interface SpielContextValue {
  spielService: SpielService;
}

interface Props {
  children: ReactNode;
}

const SpielContext = React.createContext<SpielContextValue>({ spielService: new SpielService() });

export function useSpielService(): SpielService {
  const { spielService } = useContext(SpielContext);

  return spielService;
}

export function SpielProvider({ children }: Props): JSX.Element {
  const { current: spielService } = useRef(new SpielService());

  return <SpielContext.Provider value={{ spielService }}>{children}</SpielContext.Provider>;
}
