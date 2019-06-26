var express = require('express'),
    app = express();
app.use(express.json());

const admin = require('firebase-admin');
let serviceAccount = require('./eisss19ruckschmele-a9a42-2109baa5fe8a.json');
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
});

app.use('/anzeige',require('./anzeige'));

var server = app.listen(process.env.PORT || 7000, () => {
  console.log("Express server listening on port %d in %s mode", server.address().port, app.settings.env);
});
