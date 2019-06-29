var express = require('express'),
    faye = require('faye'),
    app = express(),
    Client = require('node-rest-client').Client
    restClient = new Client();
app.use(express.json());
var msgClient = new faye.Client('http://localhost:3000');
var rest_url = 'http://localhost:7000/lager';
var rest_url_short = 'http://localhost:7000';

var rest_args = {
  requestConfig: {
      timeout: 1000,
      noDelay: true,
      keepAlive: true
  },
  responseConfig: {
      timeout: 1000
  }
}

msgClient.subscribe('/lager/*').withChannel((channel, message) => {

})
.then(() => {
  console.log('subscribed to /lager/*');
  var server = app.listen(process.env.PORT || 4000, () => {
    console.log("Express server listening on port %d in %s mode", server.address().port, app.settings.env);
  });
});
