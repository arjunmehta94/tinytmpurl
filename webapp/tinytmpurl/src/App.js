import React, { Component } from 'react';
import './styles/App.css';
import Url from './Url'

class App extends Component {
  render() {
    return (
      <div className="App">
        <div>Welcome to Tiny Tmp Url!</div>
        <Url />
      </div>
    );
  }
}

export default App;
