var express = require('express'),
    faye = require('faye'),
    app = express(),
    Client = require('node-rest-client').Client
    restClient = new Client();
app.use(express.json());
var msgClient = new faye.Client('http://localhost:3000');
var rest_url = 'http://localhost:7000/lager/';
var rest_url_short = 'http://localhost:7000/';
var matching = 'MatchIng';

var server = app.listen(process.env.PORT || 5000, () => {
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

msgClient.subscribe('/lager/*').withChannel((channel, message) => {
  var respond = '/antwort/' + message.origin;
  switch (channel) {
    case '/lager/neu':
      rest_args.data = message.request;
      rest_args.headers = { "Content-Type": "application/json" };
      restClient.post(rest_url, rest_args, (data, response) => {
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
    case '/lager/alle':
      if (message.origin === matching) {
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
      } else {
        msgClient.publish(respond, {
          status : 401
        });
      }
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
  console.log('subscribed to /lager/*');
});

msgClient.subscribe('/lebensmittel/neu').withChannel((channel, message) => {
  var respond = '/antwort/' + message.origin;
  rest_args.data = message.request;
  rest_args.headers = { "Content-Type": "application/json" };
  restClient.post(rest_url+message.request.lager, rest_args, (data, response) => {
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
  console.log('subscribed to /lebensmittel/neu');
});
