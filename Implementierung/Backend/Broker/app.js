var http = require('http'),
    faye = require('faye'),
    server = http.createServer(),
    bayeux = new faye.NodeAdapter({mount: '/'});

bayeux.attach(server);
server.listen(process.env.PORT || 3000, () => {
  console.log("http server listening on port %d.", server.address().port);
});

var everything = '/**';

bayeux.getClient().subscribe(everything).withChannel((channel, message) => {
  console.log(`new message on ${channel} @ ${new Date}:`);
  console.log(message);
})
.then(() => {
  console.log(`subscribed to ${everything}`);
});
