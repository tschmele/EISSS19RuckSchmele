var express = require('express');
var router = express.Router();

router.post('/', (req, res) => {
  res.status(501).end();
});

router.get('/', (req, res) => {
  res.status(401).end();
});

router.get('/:id', (req,res) => {
  if (req.header('origin') === undefined) {
    res.statusMessage = 'origin undefined';
    res.status(400).end();
  } else {
    msgClient.subscribe('/antwort/' + req.header('origin'), message => {
      if (message.statusMessage)
        res.statusMessage = message.statusMessage;
      res.status(message.status).json(message.results);
    }).then(() => {
      msgClient.publish('/lager/' + req.params.id, {
        origin : req.header('origin'),
        action : "get"
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
      if (message.statusMessage)
        res.statusMessage = message.statusMessage;
      res.status(message.status).json(message.results);
    }).then(() => {
      msgClient.publish('/lager/' + req.params.id, {
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
      if (message.statusMessage)
        res.statusMessage = message.statusMessage;
      res.status(message.status).json(message.results);
    }).then(() => {
      msg.publish('/anzeige/' + req.params.id, {
        origin : req.header('origin'),
        action : "delete"
      });
    });
  }
});

module.exports = router;
