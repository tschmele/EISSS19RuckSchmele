var express = require('express'),
    router = express.Router(),
    anzeige = 'Anzeige';

const admin = require('firebase-admin');
let serviceAccount = require('./eisss19ruckschmele-a9a42-2109baa5fe8a.json');
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
}, 'anzeige');
let db = admin.firestore();

/*******************************************************************************

*******************************************************************************/
router.post('/', (req, res) => {
  var anzeige_neu = req.body;

  anzeige_neu.autor = db.collection('/Benutzer/').doc(anzeige_neu.autor);
  anzeige_neu.standort = new admin.firestore.GeoPoint(anzeige_neu.standort._latitude, anzeige_neu.standort._longitude);
  if (anzeige_neu.verbrauch)
    anzeige_neu.verbrauch.datum = admin.firestore.Timestamp.fromDate(new Date(anzeige_neu.verbrauch.datum));
  anzeige_neu.reserviert = false;

  db.collection(anzeige).add(anzeige_neu)
    .then(doc => {
      anzeige_neu.autor.get()
      .then(autor => {
        anzeige_neu.autor = {
          id : autor.id,
          name : autor.data().name
        }
        return res.status(201).json({
          id : doc.id,
          data : anzeige_neu
        });
      });
    })
    .catch(err => {
      return res.status(502).json({error : err});
    });
});

/*******************************************************************************

*******************************************************************************/
router.get('/', (req, res) => {
  db.collection(anzeige).get()
  .then(snapshot => {
    var promises = [];
    snapshot.forEach(doc => {
      var prom_new = new Promise((resolve, reject) => {
        doc.data().autor.get()
        .then(autor_doc => {
          var anz = doc.data();
          anz.autor = {
            id : autor_doc.id,
            name : autor_doc.data().name
          };
          resolve({
            id : doc.id,
            data : anz
          });
        })
        .catch(err => {
          reject(err);
        });
      });
      promises.push(prom_new);
    });

    Promise.all(promises).then(results => {
      return res.status(200).json(results);
    }).catch(errmsg => {
      return res.status(500).json({error : errmsg});
    })
  })
  .catch(err => {
    return res.status(502).json({error : err});
  });
});

/*******************************************************************************

*******************************************************************************/
router.get('/:id', (req, res) => {
  db.collection(anzeige).doc(req.params.id).get()
  .then(doc => {
    if (!doc.exists) {
      return res.status(404).json();
    } else {
      doc.data().autor.get()
      .then(autor => {
        var anz = doc.data();
        anz.autor = {
          id : autor.id,
          name : autor.data().name
        }
        return res.status(200).json({
          id : doc.id,
          data : anz
        });
      });
    }
  })
  .catch(err => {
    return res.status(502).json({error : err});
  });
});

/*******************************************************************************

*******************************************************************************/
router.put('/:id', (req, res) => {
  if (req.body.standort)
    req.body.standort = new admin.firestore.GeoPoint(req.body._latitude, req.body._longitude);
  if (req.body.verbrauch)
    req.body.verbrauch.datum = admin.firestore.Timestamp.fromDate(new Date(req.body.verbrauch.datum));

  db.collection(anzeige).doc(req.params.id).update(req.body)
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
  db.collection(anzeige).doc(req.params.id).delete()
  .then(() => {
    return res.status(204).json();
  })
  .catch(err => {
    return res.status(502).json({error : err});
  });
});

module.exports = router;
