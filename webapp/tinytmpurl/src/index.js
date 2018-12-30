import React from 'react';
import ReactDOM from 'react-dom';
import './styles/index.css';
import App from './App';
import Redirect from './Redirect';
import { Route, BrowserRouter as Router } from 'react-router-dom';

const routing = (
  <Router>
    <div>
      <Route exact path="/" component={App} />
      <Route path="/:route" component={Redirect} />
    </div>
  </Router>
)

ReactDOM.render(routing, document.getElementById('root'));

