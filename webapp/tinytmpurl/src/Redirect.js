import React, { Component } from 'react';
import axios from 'axios';

class Redirect extends Component {
  constructor(props) {
    super(props);
    this.state = {
      url: props.match.params.route
    }
  }

  componentDidMount() {
    axios.get('http://127.0.0.1:8090/url/' + this.state.url)
        .then(function(response) {
          console.log(response.data);
          window.location = response.data;
        }).catch(function(error) {
          console.log(error);
        })
  }

  render() {
    return (
      <div className="Redirect">
        <div>go!</div>
        <div>{this.state.url}</div>
      </div>
    );
  }
}

export default Redirect;
