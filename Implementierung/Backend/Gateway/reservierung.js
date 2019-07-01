var express = require('express'),
    faye = require('faye');

var router = express.Router(),
    msgClient = new faye.Client('http://localhost:3000');

router.post('/', (req, res) => {
  if (req.body.anzeige === undefined) {
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
      msgClient.publish('/reservierung/neu', {
        origin : req.header('origin'),
        action : "post",
        request : req.body
      });
    });
  }
});

router.put('/:id', (req, res) => {
  return res.status(501).end();
});

router.delete('/', (req, res) => {
  if (req.header('origin') === undefined) {
    res.statusMessage = 'origin undefined';
    return res.status(400).end();
  } else {
    msgClient.subscribe('/antwort/' + req.header('origin'), message => {
      if (message.statusMessage)
        res.statusMessage = message.statusMessage;
      res.status(message.status).json(message.results);
      return msgClient.unsubscribe('/antwort/' + req.header('origin'));
    }).then(() => {
      msgClient.publish('/reservierung/' + req.params.id, {
        origin : req.header('origin'),
        action : "delete",
        request : req.body
      });
    });
  }
});

module.exports = router;
