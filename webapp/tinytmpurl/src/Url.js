import React, { Component } from 'react';
import DateTimePicker from 'react-datetime-picker';
import { postUrl } from './utils/urlmapper';
import { isEmail, isDateTime } from './utils/validation';
import UrlInput from './components/url';

class UrlResult extends Component {
  render() {
    return (
      <div className="result">
        <div>Here is the result:</div>
        <div>{this.props.result}</div>
      </div>
    );
  }
}
class Url extends Component {

  constructor(props) {
    super(props);
    this.state = {
      email: '',
      url: '',
      date: new Date(),
      result: null
    }
  }

  handleEmailChange(event) {
    const email = event.target.value;
    if (isEmail(email)) {
      this.setState({
        email: email
      });
    } else {
      this.setState({
        result: 'Please enter valid email'
      })
    }
  }

  handleUrlChange(url) {
    this.setState({
      url: url
    });
  }

  handleDateChange(date) {
    if (isDateTime(date)) {
      this.setState({
        date: date
      });
    } else {
      this.setState({
        result: 'Please enter valid future date'
      })
    }
  }

  handleSubmit() {
    postUrl(this.state.email, this.state.url, this.state.date)
      .then(function(response) {
        console.log(response);
        const url = 'https://tmptinyurl.com/' + response.data;
        this.setState({
          result: url
        })
      }.bind(this), function(error) {
        console.log(error);
        this.setState({
          result: 'Sorry! Something went wrong'
        })
      }.bind(this));
  }

  render() {
    return !this.state.result ? (
      <div className="Url">
        <div className="email">
            <input type="text" name="email" value={this.state.email}
            placeholder="abc@def.com"
            onChange={this.handleEmailChange.bind(this)} />
        </div>
        <UrlInput handleUrlChange={this.handleUrlChange.bind(this)} />
        <div className="date">
            <DateTimePicker
              onChange={this.handleDateChange.bind(this)}
              value={this.state.date}/>
        </div>
        <button onClick={this.handleSubmit.bind(this)}>Submit</button>
      </div>
    ) : <UrlResult result={this.state.result} />;
  }
}

export default Url;