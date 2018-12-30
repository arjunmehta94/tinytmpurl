import React, { Component } from 'react';
import DateTimePicker from 'react-datetime-picker';
import axios from 'axios';

class Url extends Component {

  constructor(props) {
    super(props);
    this.state = {
      email: '',
      url: '',
      date: new Date()
    }
  }

  handleEmailChange(event) {
    this.setState({
      email: event.target.value
    });
  }

  handleUrlChange(event) {
    this.setState({
      url: event.target.value
    });
  }

  handleDateChange(date) {
    this.setState({
      date: date
    });
  }

  handleSubmit() {
    console.log('email', this.state.email);
    console.log('url', this.state.url);
    console.log('date', this.state.date);
    const date = this.state.date.toISOString();
    axios.post('http://127.0.0.1:8090/url', {
      email: this.state.email,
      resource: this.state.url,
      expiration: date
    })
        .then(function(response) {
          console.log('response');
          console.log(response);
          //window.location = response.data;
        }).catch(function(error) {
          console.log(error);
        })
  }

  render() {
    return (
      <div className="Url">
        <div className="email">
            <input type="text" name="email" value={this.state.email}
            placeholder="abc@def.com"
            onChange={this.handleEmailChange.bind(this)} />
        </div>
        <div className="url">
            <input size="200" type="text" name="url" value={this.state.url}
            placeholder="https://www.google.com/"
            onChange={this.handleUrlChange.bind(this)} />
        </div>
        <div className="date">
            <DateTimePicker
              onChange={this.handleDateChange.bind(this)}
              value={this.state.date}/>
        </div>
        <button onClick={this.handleSubmit.bind(this)}>Submit</button>
      </div>
    );
  }
}

export default Url;