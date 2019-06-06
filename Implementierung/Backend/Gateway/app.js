var express = require('express'),
    app = express();
app.use(express.json());

app.get('/', (req, res) => {
  res.json('hello world');
});

app.use('/anzeige',require('./anzeige'));
app.use('/reservierung',require('./reservierung'));
app.use('/benutzer', require('./benutzer'));
app.use('/lager',require('./lager'));
app.use('/information',(req, res) => {
  res.status(501).end();
});

app.put('/lebensmittel', (req, res) => {
  res.statusMessage = 'created';
  res.status(204).end();
});

var server = app.listen(process.env.PORT || 8080, () => {
  console.log("Express server listening on port %d in %s mode", server.address().port, app.settings.env);
});
