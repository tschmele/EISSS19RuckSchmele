var express = require('express'),
    faye = require('faye'),
    Client = require('node-rest-client').Client
    GeoPoint = require('geopoint'),
    app = express(),
    restClient = new Client();
app.use(express.json());
var msgClient = new faye.Client('http://localhost:3000');
var rest_url = 'http://localhost:7000/benutzer/';
var rest_url_short = 'http://localhost:7000/';
var matching = 'MatchIng';

var server = app.listen(process.env.PORT || 6000, () => {
  console.log("Express server listening on port %d in %s mode", server.address().port, app.settings.env);
});

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

msgClient.subscribe('/benutzer/*').withChannel((channel, message) => {
  var respond = '/antwort/' + message.origin;
  switch (channel) {
    case '/benutzer/neu':
      msgClient.publish(respond, {
        status : 501,
        results : {}
      });
      break;
    case '/benutzer/alle':
      msgClient.publish(respond, {
        status : 501,
        results : {}
      });
      break;
    default:
      switch (message.action) {
        case 'get':
          restClient.get(rest_url_short+channel, rest_args, (data, response) => {
            msgClient.publish(respond, {
              status : response.statusCode,
              results : data
            });
          }).on('error', err => {
            msgClient.publish(respond, {
              status : 500,
              results : {
                error : err
              }
            });
          });
          break;
        case 'put':
          rest_args.data = message.request;
          rest_args.headers = { "Content-Type": "application/json" };
          restClient.put(rest_url_short+channel, rest_args, (data, response) => {
            msgClient.publish(respond, {
              status : response.statusCode,
              results : data
            });
          }).on('error', err => {
            msgClient.publish(respond, {
              status : 500,
              results : {
                error : err
              }
            });
          });
          break;
        case 'delete':
          restClient.delete(rest_url_short+channel, rest_args, (data, response) => {
            msgClient.publish(respond, {
              status : response.statusCode
            });
          }).on('error', err => {
            msgClient.publish(respond, {
              status : 500,
              results : {
                error : err
              }
            });
          });
          break;
        default:
          msgClient.publish(respond, {
            status : 400,
            statusMessage : 'invalid action'
          });
      }
  }
})
.then(() => {
  console.log('subscribed to /benutzer/*');
});

msgClient.subscribe('/kommentar/neu').withChannel((channel, message) => {
  var respond = '/antwort/' + message.origin;
  rest_args.data = message.request.kommentar;
  rest_args.headers = { "Content-Type": "application/json" };
  restClient.post(rest_url+message.request.benutzer, rest_args, (data, response) => {
    msgClient.publish(respond, {
      status : response.statusCode,
      results : data
    });
  }).on('error', err => {
    msgClient.publish(respond, {
      status : 500,
      results : {
        error : err.toString()
      }
    });
  });
})
.then(() => {
  console.log('subscribed to /kommentar/neu');
});

msgClient.subscribe('/matching/benutzer').withChannel((channel, message) => {
  var origin = new GeoPoint(message.standort._latitude, message.standort._longitude);
  restClient.get(rest_url, rest_args, (data, response) => {
    var possible_matches = [];
    data.forEach(user => {
      var userLocation = new GeoPoint(user.data.standort._latitude, user.data.standort._longitude);
      var userDistance = userLocation.distanceTo(origin, true);
      if (userDistance <= message.radius && user.id != message.autor) {
        possible_matches.push({
          id : user.id,
          data : user.data,
          distance : userDistance
        });
      }
    });

    msgClient.publish('/matching/'+message.id, {
      status : response.statusCode,
      results : possible_matches
    });
  }).on('error', err => {
    msgClient.publish(respond, {
      status : 500,
      results : {
        error : err.toString()
      }
    });
  });
})
.then(() => {
  console.log('subscribed to /matching/benutzer');
});
