var http = require('http'),
    faye = require('faye'),
    server = http.createServer(),
    bayeux = new faye.NodeAdapter({mount: '/'});

var demo = false;

bayeux.attach(server);
server.listen(process.env.PORT || 3000, () => {
  console.log("http server listening on port %d.", server.address().port);
});

var everything = '/**';

bayeux.getClient().subscribe(everything, message => {
  console.log(`new message @ ${new Date}:`);
  console.log(message);
  if (demo && message.origin != undefined) {
    bayeux.getClient().publish('/antwort/' + message.origin, {
      status : 200,
      statusMessage : 'top brudi',
      results : {
        text : 'test erfolgreich'
      }
    });
  }
})
.then(() => {
  console.log(`subscribed to ${everything}`);
});
