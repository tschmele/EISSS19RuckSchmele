var express = require('express'),
    faye = require('faye');

var router = express.Router(),
    msgClient = new faye.Client('http://localhost:3000');

/*******************************************************************************
  benutzer
*******************************************************************************/
router.post('/', (req, res) => {
  return res.status(501).end();
});

router.get('/', (req, res) => {
  return res.status(401).end();
});

router.get('/:id', (req, res) => {
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
      msgClient.publish('/benutzer/' + req.params.id, {
        origin : req.header('origin'),
        action : "get"
      });
    });
  }
});

router.put('/:id', (req, res) => {
  return res.status(501).end();
});

/*******************************************************************************
  kommentar
*******************************************************************************/
router.post('/:id/kommentar', (req, res) => {
  if (req.body.autor === undefined || req.body.wertung === undefined) {
    return res.status(400).end();
  }  else if (req.header('origin') === undefined) {
    res.statusMessage = 'origin undefined';
    return res.status(400).end();
  } else {
    msgClient.subscribe('/antwort/' + req.header('origin'), message => {
      if (message.statusMessage)
        res.statusMessage = message.statusMessage;
      res.status(message.status).json(message.results);
      return msgClient.unsubscribe('/antwort/' + req.header('origin'));
    }).then(() => {
      msgClient.publish('/kommentar/neu', {
        origin : req.header('origin'),
        action : "post",
        request : {
          benutzer : req.params.id,
          kommentar : req.body
        }
      });
    });
  }
});

router.delete('/:user/kommentar/:comment', (req, res) => {
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
      msgClient.publish('/kommentar/' + req.params.comment, {
        origin : req.header('origin'),
        action : "delete",
        user : req.params.user
      });s
    });
  }
});

module.exports = router;
