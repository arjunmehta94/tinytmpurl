import axios from 'axios';

function getUrl(url) {
  return axios.get('http://127.0.0.1:8090/url/' + url);
}

function postUrl(email, resource, expiration) {
  const date = expiration.toISOString();
  return axios.post('http://127.0.0.1:8090/url', {
    email: email,
    resource: resource,
    expiration: date
  });
}

export {
  getUrl,
  postUrl
}
