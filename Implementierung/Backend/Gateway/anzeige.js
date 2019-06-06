var express = require('express');
var router = express.Router();

router.post('/', (req, res) => {
  if (req.body.autor === undefined) {
    res.status(400).end();
  } else {
    res.status(201).json('neue anzeige');
  }
});

router.get('/', (req, res) => {
  if (req.query.radius != null && req.query.radius > 0) {
    res.status(200).json(`alle anzeigen in ${req.query.radius} km umkreis`);
  } else {
    res.status(200).json('alle anzeigen');
  }
});

router.get('/:id', (req, res) => {
  res.status(200).json(`anzeige mit ID ${req.params.id}`);
});

router.put('/:id', (req, res) => {
  res.status(200).json(`aktualisierte anzeige mit ID ${req.params.id}`);
});

router.delete('/:id', (req, res) => {
  res.statusMessage = `deleted`;
  res.status(204).end();
});

module.exports = router;
