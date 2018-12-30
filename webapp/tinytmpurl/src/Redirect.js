import React, { Component } from 'react';
import { getUrl } from './utils/urlmapper';

class BadUrl extends Component {

  render() {
    return (
      <div className="badurl">
        <div>Sorry!</div>
        <div>{this.props.url} is not a valid mapping</div>
      </div>
    );
  }
}

class Redirect extends Component {
  constructor(props) {
    super(props);
    this.state = {
      url: props.match.params.route,
      badUrl: false
    }
  }

  componentDidMount() {
    getUrl(this.state.url)
      .then(function(response) {
        window.location = response.data;
      }, function(error) {
        this.setState({
          badUrl: true
        });
      }.bind(this));
  }

  render() {
    return this.state.badUrl ? <BadUrl url={this.state.url}/> : null;
  }
}

export default Redirect;
