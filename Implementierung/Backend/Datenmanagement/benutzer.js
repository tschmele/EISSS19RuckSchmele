var express = require('express'),
    router = express.Router(),
    benutzer = 'Benutzer';

const admin = require('firebase-admin');
let serviceAccount = require('./eisss19ruckschmele-a9a42-2109baa5fe8a.json');
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
}, 'benutzer');
let db = admin.firestore();

/*******************************************************************************
Delayed. Nicht notwendug für den Prototypen
*******************************************************************************/
router.post('/', (req, res) => {
  return res.status(501).json();
});

/*******************************************************************************
  TODO
  > Kommentar-Autoren übersetzen
    > id und name
  > Reservierungen übersetzen
    > id und titel
  > Lager übersetzen
    > alles
*******************************************************************************/
router.get('/', (req, res) => {
  db.collection(benutzer).get()
  .then(snapshot => {

  })
  .catch(err => {
    return res.status(502).json({error : err});
  });
});

/*******************************************************************************
  TODO
  > Kommentar-Autoren übersetzen
    > id und name
  > Reservierungen übersetzen
    > id und titel
  > Lager übersetzen
    > alles
*******************************************************************************/
router.get('/:id', (req, res) => {
  db.collection(benutzer).doc(req.params.id).get()
  .then(doc => {
    var reservation_prom = new Promise((resolve, reject) => {
      if (doc.data().reservierungen) {
        var res_proms = [];

        doc.data().reservierungen.forEach(reservierung => {
          var prom = new Promise((resolve, reject) => {
            reservierung.get()
            .then(reserv_doc => {
              resolve({
                id : reserv_doc.id,
                titel : reserv_doc.data().titel
              });
            })
            .catch(error => {
              reject();
            });
          });
          res_proms.push(prom);
        });

        Promise.all(res_proms).then(results => {
          resolve(results);
        })
        .catch(errmsg => {
          return res.end();
        });
      } else {
        resolve();
      }
    });

    var comment_prom = new Promise((resolve, reject) => {
      if (doc.data().kommentare) {
        var com_proms = [];

        doc.data().kommentare.forEach(kommentar => {
          var prom = new Promise((resolve, reject) => {
            kommentar.autor.get()
            .then(autor_doc => {
              resolve({
                autor : {
                  id : autor_doc.id,
                  name : autor_doc.data().name
                },
                kommentar : kommentar.kommentar,
                wertung : kommentar.wertung
              });
            })
            .catch(error => {
              reject();
            });
          });
          com_proms.push(prom);
        });

        Promise.all(com_proms).then(results => {
          resolve(results);
        })
        .catch(errmsg => {
          return res.end();
        });
      } else {
        resolve();
      }
    });
    var storage_prom = new Promise((resolve, reject) => {
      doc.data().lager.get()
      .then(lag_doc => {
        resolve({
          id : lag_doc.id,
          data : lag_doc.data()
        });
      })
      .catch(error => {
        reject();
      });
    });
    var promises = [reservation_prom, comment_prom, storage_prom];

    Promise.all(promises).then(results => {
      var user = doc.data();
      if (user.reservierungen)
        user.reservierungen = results[0];
      if (user.kommentare)
        user.kommentare = results[1];
      user.lager = results[2];
      return res.status(200).json({
        id : doc.id,
        data : user
      });
    });
  })
  .catch(err => {
    return res.status(502).json({error : "firestore"});
  });
});

/*******************************************************************************
  WIP. Ersetzt bisher lediglich die vorhandenen Daten
*******************************************************************************/
router.put('/:id', (req, res) => {

  db.collection(benutzer).doc(req.params.id).set(req.body, {merge : true})
  .then(doc => {
      return res.status(204).json();
  })
  .catch(err => {
    return res.status(502).json({error : err});
  });
});

/*******************************************************************************
  Delayed. Nicht notwendug für den Prototypen
*******************************************************************************/
router.delete('/:id', (req, res) => {
  return res.status(501).json();
});

module.exports = router;
