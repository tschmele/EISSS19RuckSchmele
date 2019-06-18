var express = require('express');
var router = express.Router();

/*******************************************************************************
  benutzer
*******************************************************************************/
router.post('/', (req, res) => {
  res.status(501).end();
});

router.get('/', (req, res) => {
  res.status(401).end();
});

router.get('/:id', (req, res) => {
  if (req.header('origin') === undefined) {
    res.statusMessage = 'origin undefined';
    res.status(400).end();
  } else {
    msgClient.subscribe('/antwort/' + req.header('origin'), message => {
      if (res.statusMessage)
        res.statusMessage = message.statusMessage;
      res.status(message.status).json(message.results);
    }).then(() => {
      msgClient.publish('/benutzer/' + req.params.id, {
        origin : req.header('origin'),
        action : "get"
      });
    });
  }
});

router.put('/:id', (req, res) => {
  res.status(501).end();
});

/*******************************************************************************
  kommentar
*******************************************************************************/
router.post('/:id/kommentar', (req, res) => {
  if (req.body.autor === undefined || req.body.wertung === undefined) {
    res.status(400).end();
  }  else if (req.header('origin') === undefined) {
    res.statusMessage = 'origin undefined';
    res.status(400).end();
  } else {
    msgClient.subscribe('/antwort/' + req.header('origin'), message => {
      if (res.statusMessage)
        res.statusMessage = message.statusMessage;
      res.status(message.status).json(message.results);
    }).then(() => {
      msgClient.publish('/kommentar/neu', {
        origin : req.header('origin'),
        action : "post",
        request : req.body
      });
    });
  }
});

router.delete('/:user/kommentar/:comment', (req, res) => {
  if (req.header('origin') === undefined) {
    res.statusMessage = 'origin undefined';
    res.status(400).end();
  } else {
    msgClient.subscribe('/antwort/' + req.header('origin'), message => {
      if (res.statusMessage)
        res.statusMessage = message.statusMessage;
      res.status(message.status).json(message.results);
    }).then(() => {
      msg.publish('/kommentar/' + req.params.comment, {
        origin : req.header('origin'),
        action : "delete",
        user : req.params.user
      });s
    });
  }
});

module.exports = router;
