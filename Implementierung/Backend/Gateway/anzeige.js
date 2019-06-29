var express = require('express'),
    faye = require('faye');

var router = express.Router(),
    msgClient = new faye.Client('http://localhost:3000');

/*******************************************************************************

*******************************************************************************/
router.post('/', (req, res) => {
  if (req.body.anfrage === undefined || req.body.standort === undefined ||
    req.body.titel === undefined) {
    return res.status(400).end();
  } else if (req.header('origin') === undefined) {
    res.statusMessage = 'origin undefined';
    return res.status(400).end();
  } else {
    msgClient.subscribe('/antwort/' + req.header('origin'), message => {
      if (message.statusMessage)
        res.statusMessage = message.statusMessage;
      if (message.status === 204 || (message.status >= 400 && message.status < 500))
        res.status(message.status).end();
      else
        res.status(message.status).json(message.results);
      return msgClient.unsubscribe('/antwort/' + req.header('origin'));
    }).then(() => {
      if (req.body.autor === undefined)
        req.body.autor = req.header('origin');
      msgClient.publish('/anzeige/neu', {
        origin : req.header('origin'),
        action : "post",
        request : req.body
      });
    });
  }
});

/*******************************************************************************

*******************************************************************************/
router.get('/', (req, res) => {
  if (req.header('origin') === undefined) {
    res.statusMessage = 'origin undefined';
    return res.status(400).end();
  } else {
    msgClient.subscribe('/antwort/' + req.header('origin'), message => {
      if (message.statusMessage) {
        res.statusMessage = message.statusMessage;
      }
      res.status(message.status).json(message.results);
      return msgClient.unsubscribe('/antwort/' + req.header('origin'));
    }).then(() => {
      if (req.query.radius != null && req.query.radius > 0) {
        msgClient.publish('/anzeige/alle', {
          origin : req.header('origin'),
          action : "get",
          radius : req.query.radius
        });
      } else {
        msgClient.publish('/anzeige/alle', {
          origin : req.header('origin'),
          action : "get"
        });
      }
    });
  }
});

/*******************************************************************************

*******************************************************************************/
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
      msgClient.publish('/anzeige/' + req.params.id, {
        origin : req.header('origin'),
        action : "get"
      });
    });
  }
});

/*******************************************************************************

*******************************************************************************/
router.put('/:id', (req, res) => {
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
      msgClient.publish('/anzeige/' + req.params.id, {
        origin : req.header('origin'),
        action : "put",
        request : req.body
      });
    });
  }
});

/*******************************************************************************

*******************************************************************************/
router.delete('/:id', (req, res) => {
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
      msgClient.publish('/anzeige/' + req.params.id, {
        origin : req.header('origin'),
        action : "delete"
      });
    });
  }
});

module.exports = router;
