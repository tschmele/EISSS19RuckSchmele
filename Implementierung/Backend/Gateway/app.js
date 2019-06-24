var express = require('express'),
    faye = require('faye'),
    app = express();
app.use(express.json());
var msgClient = new faye.Client('http://localhost:3000');

app.get('/', (req, res) => {
  res.json('hello world');
});

app.use('/anzeige',require('./anzeige'));
app.use('/reservierung',require('./reservierung'));
app.use('/benutzer', require('./benutzer'));
app.use('/lager',require('./lager'));
app.use('/information',(req, res) => {
  return res.status(501).end();
});

app.post('/lebensmittel', (req, res) => {
  if (req.body.lebensmittel === undefined) {
    return res.status(400).end();
  } else if (req.header('origin') === undefined) {
    res.statusMessage = 'origin undefined';
    return res.status(400).end();
  } else {
    msgClient.subscribe('/antwort/' + req.header('origin'), message => {
      if (message.statusMessage)
        res.statusMessage = message.statusMessage;
      res.status(message.status).json(message.results);
      return msgClient.unsubscribe('/antwort/' + req.header('origin'));
    }).then(() => {
      msgClient.publish('/lebensmittel/neu', {
        origin : req.header('origin'),
        action : "post",
        request : req.body
      });
    });
  }
});

var server = app.listen(process.env.PORT || 2000, () => {
  console.log("Express server listening on port %d in %s mode", server.address().port, app.settings.env);
});
