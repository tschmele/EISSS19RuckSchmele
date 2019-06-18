var express = require('express');
var router = express.Router();

router.post('/', (req, res) => {
  if (req.body.anzeige === undefined) {
    res.status(400).end();
  } else if (req.header('origin') === undefined) {
    res.statusMessage = 'origin undefined';
    res.status(400).end();
  } else {
    msgClient.subscribe('/antwort/' + req.header('origin'), message => {
      if (res.statusMessage)
        res.statusMessage = message.statusMessage;
      res.status(message.status).json(message.results);
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
  if (req.header('origin') === undefined) {
    res.statusMessage = 'origin undefined';
    res.status(400).end();
  } else {
    msgClient.subscribe('/antwort/' + req.header('origin'), message => {
      if (res.statusMessage)
        res.statusMessage = message.statusMessage;
      res.status(message.status).json(message.results);
    }).then(() => {
      msgClient.publish('/reservierung/' + req.params.id, {
        origin : req.header('origin'),
        action : "put",
        request : req.body
      });
    });
  }
});

router.delete('/:id', (req, res) => {
  if (req.header('origin') === undefined) {
    res.statusMessage = 'origin undefined';
    res.status(400).end();
  } else {
    msgClient.subscribe('/antwort/' + req.header('origin'), message => {
      if (res.statusMessage)
        res.statusMessage = message.statusMessage;
      res.status(message.status).json(message.results);
    }).then(() => {
      msg.publish('/reservierung/' + req.params.id, {
        origin : req.header('origin'),
        action : "delete"
      });
    });
  }
});

module.exports = router;
