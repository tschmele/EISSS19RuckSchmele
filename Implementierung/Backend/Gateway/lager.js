var express = require('express');
var router = express.Router();

router.post('/', (req, res) => {
  res.status(501).end();
});

router.get('/', (req, res) => {
  res.status(401).end();
});

router.get('/:id', (req,res) => {
  res.status(200).json(`lager mit ID ${req.params.id}`);
});

router.put('/:id', (req, res) => {
  res.status(200).json(`geändertes lager mit ID ${req.params.id}`);
});

router.delete('/:id', (req, res) => {
  res.statusMessage = 'deleted';
  res.status(204).end();
});

module.exports = router;
