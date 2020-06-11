import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './view/App';
import { CssBaseline } from '@material-ui/core';

ReactDOM.render(
  <React.StrictMode>
    <CssBaseline />

    <App />
  </React.StrictMode>,
  document.getElementById('root')
);
