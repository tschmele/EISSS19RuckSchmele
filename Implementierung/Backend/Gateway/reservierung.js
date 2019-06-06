var express = require('express');
var router = express.Router();

router.post('/', (req, res) => {
  if (req.body.anzeige === undefined) {
    res.status(400).end();
  } else {
    res.status(201).json(`anzeige mit ID ${req.body.anzeige} reserviert`);
  }
});

router.put('/:id', (req, res) => {
  res.status(200).json(`aktualisierte reservierung mit ID ${req.params.id}`)
});

router.delete('/:id', (req, res) => {
  res.statusMessage = `deleted`;
  res.status(204).end();
});

module.exports = router;
