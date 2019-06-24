const admin = require('firebase-admin');

let serviceAccount = require('./eisss19ruckschmele-a9a42-82a162090948.json');

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
});

let db = admin.firestore();
