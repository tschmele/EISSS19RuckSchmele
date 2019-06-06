var express = require('express');
var router = express.Router();

/*******************************************
  benutzer
*******************************************/
router.post('/', (req, res) => {
  res.status(501).end();
});

router.get('/', (req, res) => {
  res.status(401).end();
});

router.get('/:id', (req, res) => {
  res.status(200).json(`benutzer mit ID ${req.params.id}`);
});

router.put('/:id', (req, res) => {
  res.status(501).end();
});

/*******************************************
  kommentar
*******************************************/
router.post('/:id/kommentar', (req, res) => {
  if (req.body.autor === undefined) {
    res.status(400).end();
  } else {
    res.status(201).json('neuer kommentar');
  }
});

router.delete('/:id1/kommentar/:id2', (req, res) => {
  res.statusMessage = 'deleted';
  res.status(204).end();
});

module.exports = router;
