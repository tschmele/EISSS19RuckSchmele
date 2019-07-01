var express = require('express'),
    router = express.Router(),
    lager = 'Lager';

const admin = require('firebase-admin');
let serviceAccount = require('./eisss19ruckschmele-a9a42-2109baa5fe8a.json');
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
}, 'lager');
let db = admin.firestore();

/*******************************************************************************

*******************************************************************************/
router.post('/', (req, res) => {
  var lager_neu = req.body;

  db.collection(lager).add(lager_neu)
  .then(doc => {
    return res.status(201).json({
      id : doc.id,
      data : lager_neu
    });
  })
  .catch(err => {
    return res.status(502).json({error : err});
  });
});

/*******************************************************************************

*******************************************************************************/
router.get('/', (req, res) => {
  db.collection(lager).get()
  .then(snapshot => {
    if (!snapshot.empty) {
      var all_docs = [];
      snapshot.forEach(doc => {
        all_docs.push({
          id : doc.id,
          data : doc.data()
        });
      });
      return res.status(200).json(all_docs);
    } else
      return res.status(404).json();
  })
  .catch(err => {
    return res.status(502).json({error : err});
  });
});

/*******************************************************************************

*******************************************************************************/
router.get('/:id', (req, res) => {
  db.collection(lager).doc(req.params.id).get()
  .then(doc => {
    if (!doc.exists) {
      return res.status(404).json();
    } else {
      return res.status(200).json({
        id : doc.id,
        data : doc.data()
      });
    }
  })
  .catch(err => {
    return res.status(502).json({error : err});
  });
});

/*******************************************************************************
  WIP. Ersetzt bisher lediglich die vorhandenen Daten
*******************************************************************************/
router.put('/:id', (req, res) => {
  if (req.body.verbrauch)
    req.body.verbrauch.datum = admin.firestore.Timestamp.fromDate(new Date(req.body.verbrauch.datum));

  db.collection(lager).doc(req.params.id).update(req.body)
  .then(doc => {
    return res.status(204).json();
  })
  .catch(err => {
    return res.status(502).json({error : err});
  });
});

/*******************************************************************************

*******************************************************************************/
router.delete('/:id', (req, res) => {
  db.collection(lager).doc(req.params.id).delete()
  .then(() => {
    return res.status(204).json();
  })
  .catch(err => {
    return res.status(502).json({error : err});
  });
});

/*******************************************************************************

*******************************************************************************/
router.post('/:id', (req, res) => {
  db.collection(lager).doc(req.params.id).get()
  .then(doc => {
    var neue_lebensmittel = req.body.lebensmittel;
    var lager_data = doc.data();
    lager_data.raeume.forEach(raum => {
      if (raum.name === req.body.raum) {
        if (raum.inhalt)
          raum.inhalt = raum.inhalt.concat(neue_lebensmittel);
        else
          raum.inhalt = neue_lebensmittel;
      }
    });
    db.collection(lager).doc(req.params.id).set(lager_data)
    .then(doc => {
      return res.status(201).json({
        id : doc.id,
        data : lager_data
      });
    })
    .catch(err2 => {
      return res.status(502).json({error : err2});
    });
  })
  .catch(err => {
    return res.status(502).json({error : err});
  });
});

module.exports = router;
