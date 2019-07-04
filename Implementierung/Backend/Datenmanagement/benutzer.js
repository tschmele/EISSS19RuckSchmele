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
  Ruft alle Benutzer auf
  Übersetzt - wenn vorhanden - bei jedem Benutzer
    > Referenzen auf Reservierungen zu ID + Titel
    > Referenzen auf Kommentarautoren zu ID + Name
    > Referenz auf Lager zu ID + Lager-Daten
  Antwortet mit dem Benutzer und den übersetzten Daten
*******************************************************************************/
router.get('/', (req, res) => {
  db.collection(benutzer).get()
  .then(snapshot => {
    var big_promises = [];
    snapshot.forEach(doc => {
      var big_prom = new Promise((big_resolve, big_reject) => {
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
          big_resolve({
            id : doc.id,
            data : user
          });
        }).catch(err => {
          return res.status(500).json({error : err});
        });
      });
      big_promises.push(big_prom);
    });

    Promise.all(big_promises).then(big_results => {
      return res.status(200).json(big_results);
    }).catch(bigmistake => {
      return res.status(500).json({error : bigmistake});
    });
  })
  .catch(err => {
    return res.status(502).json({error : err});
  });
});

/*******************************************************************************
  Ruft den Benutzer mit der ID req.params.id auf
  Übersetzt - wenn vorhanden -
    > Referenzen auf Reservierungen zu ID + Titel
    > Referenzen auf Kommentarautoren zu ID + Name
    > Referenz auf Lager zu ID + Lager-Daten
  Antwortet mit dem Benutzer und den übersetzten Daten
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
    }).catch(err => {
      return res.status(500).json({error : err});
    });
  })
  .catch(err => {
    return res.status(502).json({error : err});
  });
});

/*******************************************************************************

*******************************************************************************/
router.put('/:id', (req, res) => {
  if (req.body.reservierung) {
    db.collection(benutzer).doc(req.params.id).update({
      reservierungen : admin.firestore.FieldValue.arrayRemove(
        db.collection('Anzeige').doc(req.body.anzeige)
      )
    })
    .then(doc => {
      db.collection('Anzeige').doc(req.body.anzeige).update({
        reserviert : false
      })
      .then(doc => {
        return res.status(204).json();
      })
      .catch(err => {
        return res.status(502).json({error : err});
      });
    })
    .catch(err => {
      return res.status(502).json({error : err});
    });
  } else {
    db.collection(benutzer).doc(req.params.id).set(req.body, {merge : true})
    .then(doc => {
      return res.status(204).json();
    })
    .catch(err => {
      return res.status(502).json({error : err});
    });
  }
});

/*******************************************************************************

*******************************************************************************/
router.post('/:id', (req, res) => {
  db.collection(benutzer).doc(req.params.id).update({
    kommentare : admin.firestore.FieldValue.arrayUnion(req.body)
  })
  .then(doc => {
    return res.status(201).json();
  })
  .catch(err => {
    return res.status(502).json({error : err});
  });
});

/*******************************************************************************

*******************************************************************************/
router.post('/:id/reservierung', (req, res) => {
  db.collection(benutzer).doc(req.params.id).update({
    reservierungen : admin.firestore.FieldValue.arrayUnion(
      db.collection('Anzeige').doc(req.body.anzeige)
    )
  })
  .then(doc => {
    db.collection('Anzeige').doc(req.body.anzeige).update({
      reserviert : true
    })
    .then(doc => {
      return res.status(201).json();
    })
    .catch(err => {
      return res.status(502).json({error : err});
    });
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
