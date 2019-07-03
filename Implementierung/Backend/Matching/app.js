var express = require('express'),
    faye = require('faye'),
    app = express();
app.use(express.json());
var msgClient = new faye.Client('http://localhost:3000');

var server = app.listen(process.env.PORT || 8000, () => {
  console.log("Express server listening on port %d in %s mode", server.address().port, app.settings.env);
});

msgClient.subscribe('/matching', message => {
  var anzeige = message.data;
  msgClient.subscribe('/matching/'+message.id, userResults => {
    var users = userResults.results;
    if (users.length <= 0) {
      //no possible matches. further calc canceled
    } else if (users.length === 1) {
      //one possible match. check if tags fit.
      //if "anfrage" check storage
      if (anzeige.anfrage) {
        if (checkStorage(anzeige.titel, users[0].data.lager))
          //nachricht
      } else {
        if (checkTags(anzeige.tags, users[0].data.tags))
          //nachricht
      }
    } else {
      //multiple possible matches.
      var matches = [];
      users.forEach(user => {
        if (anzeige.anfrage) {
          if (checkTags(anzeige.titel, user.data.lager))
            matches.push(user);
        } else {
          if (checkTags(anzeige.tags, user.data.tags))
            matches.push(user);
        }
      });
      //count remaining matches
      if (matches.length <= 0) {
        //no matches end program
      } else if (matches.length === 1) {
        //one final match. send message
      } else {
        //multiple valid matches. find highest rating
        var best_match = [];
        matches.forEach(match => {
          if (best_match.lenght <= 0 || best_match[0].data.wertung < match.data.wertung){
            best_match = [];
            best_match.push(match);
          } else if (best_match[0].data.wertung === match.data.wertung)
            best_match.push(match);
        });
        //check remaining matches
        if (best_match.lenght === 1) {
          //one remaining. notify
        } else if (best_match.length > 1) {
          var final_match;
          best_match.forEach(match => {
            if (final_match === undefined || final_match.distance >= match.distance)
              final_match = match;
          });
          //notify remaining match
        } else
          //smth went wrong. error
      }
    }
    msgClient.unsubscribe('/matchgin/'+message.id);
  })
  .then(() => {
    msgClient.publish('/matching/benutzer', {
      id : message.id,
      standort : message.data.standort,
      radius : 15
    });
  });
})
.then(() => {
  console.log('Subscribed to /matching');
});

function checkTags (anz_tags, user_tags) {
  var match = true;
  user_tags.forEach(user_tag => {
    anz_tags.forEach(anzeige_tag => {
      switch (user_tag) {
        case 'vegetarier':
          if (anzeige_tag === 'fleisch' || anzeige_tag === 'schwein'
            || anzeige_tag === 'rind' || anzeige_tag === 'huhn'
            || anzeige_tag === 'fisch')
            match = false;
          break;
        case 'veganer':
          if (anzeige_tag === 'fleisch' || anzeige_tag === 'schwein'
            || anzeige_tag === 'rind' || anzeige_tag === 'huhn'
            || anzeige_tag === 'fisch' || anzeige_tag === 'milch'
            || anzeige_tag === 'ei' || anzeige_tag === 'laktose')
            match = false;
          break;
        case 'laktoseintolerant':
          if (anzeige_tag === 'milch' || anzeige_tag === 'laktose')
            match = false;
          break;
        case 'stirbt an gluten':
          if (anzeige_tag === 'gluten')
            match = false;
          break;
        default:
      }
    });
  });
  return match;
}

function checkStorage (titel, storage) {
  var match = false
  storage.raeume.forEach(raum => {
    raum.inhalt.forEach(inhalt => {
      if (titel.find(inhalt.lebensmittel) >= 0)
        match = true;
    });
  });
  return match;
}
