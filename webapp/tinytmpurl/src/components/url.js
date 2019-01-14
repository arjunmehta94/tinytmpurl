import React, { Component } from 'react';
import { Overlay, Popover } from 'react-bootstrap';
import { isUrl } from '../utils/validation';

class UrlInput extends Component {

  constructor(props) {
    super(props);
    this.state = {
      show: false,
      target: null
    }
  }

  handleChange(event) {
    const url = event.target.value;
    if (isUrl(url)) {
      this.props.handleUrlChange(url);
    } else {
      this.setState({
        show: true,
        target: event.target
      });
    }
  }

  render() {
    return (
      <Overlay
        show={this.state.show}
        target={this.state.target}
        placement="bottom"
        container={this}
        containerPadding={20}>
        <Popover id="popover-contained" title="Bad email">
          <div className="url">
            <input size="200" type="url" name="url" value={this.state.url}
            placeholder="https://www.google.com/"
            onChange={this.handleChange.bind(this)} />
          </div>
        </Popover>
      </Overlay>
    );
  }
}

export default UrlInput;