var express = require('express'),
    faye = require('faye'),
    app = express(),
    Client = require('node-rest-client').Client
    restClient = new Client();
app.use(express.json());
var msgClient = new faye.Client('http://localhost:3000');
var rest_url = 'http://localhost:7000/anzeige/';
var rest_url_short = 'http://localhost:7000/';

var server = app.listen(process.env.PORT || 4000, () => {
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

/*******************************************************************************
  input
    message : {
      origin : //response to this,
      action : //what needs to be done,
      request : //anything necessary to do it
    }
  output
   message : {
     status : //mandatory
     statusMessage : //optional
     results : //optional
   }
*******************************************************************************/
msgClient.subscribe('/anzeige/*').withChannel((channel, message) => {
  var respond = '/antwort/' + message.origin;
  switch (channel) {
    case '/anzeige/neu':
      rest_args.data = message.request;
      rest_args.headers = { "Content-Type": "application/json" };
      restClient.post(rest_url, rest_args, (data, response) => {
        msgClient.publish(respond, {
          status : response.statusCode,
          results : data
        });
        msgClient.publish('/matching', {
          new : data
        });
      }).on('error', err => {
        msgClient.publish(respond, {
          status : 500,
          results : {
            error : err.toString()
          }
        });
      });
      break;
    case '/anzeige/alle':
      if (message.radius) {
        rest_args.parameters = {radius : message.radius};
        rest_args.headers = {origin : message.origin};
      }
      restClient.get(rest_url, rest_args, (data, response) => {
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
  console.log('subscribed to /anzeige/*');
});

msgClient.subscribe('/reservierung/*').withChannel((channel, message) => {
  var respond = '/antwort/' + message.origin;
  switch (channel) {
    case '/reservierung/neu':
      rest_args.data = message.request;
      rest_args.headers = { "Content-Type": "application/json" };
      restClient.post(rest_url_short+'benutzer/'+message.origin+'/reservierung', rest_args, (data, response) => {
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
      break;
    default:
      switch (message.action) {
        case 'delete':
          rest_args.data = {
            anzeige : message.request.anzeige,
            reservierung : true
          };
          rest_args.headers = { "Content-Type": "application/json" };
          restClient.put(rest_url_short+'benutzer/'+message.origin, rest_args, (data, response) => {
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
  console.log('subscribed to /reservierung/*');
});
