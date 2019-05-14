var express = require('express'),
	fs = require('fs'),
	chalk = require('chalk');


var app = express();
var testdata = __dirname + '/' + 'test-data.json';

app.get('/', (req, res) => {
	res.end(JSON.stringify('hello world'));
})

app.get('/listUsers', (req, res) => {
	fs.readFile(testdata, (err, data) => {
		if (err) throw err;

		console.log( data );
		res.end( data );
	});
});

app.post('/addUser', (req,res) => {
	fs.readFile(testdata, 'utf8', (err, data) => {
		if (err) throw err;

		var all_data = JSON.parse(data);

		var newData = {
				"name" : req.query.name,
				"group" : req.query.group,
				"storage" : null
		};

		all_data.users.push(newData);
		fs.writeFile(testdata, JSON.stringify(all_data), err => {
			if (err) throw err;
			res.end(JSON.stringify(all_data));
			console.log(all_data);
		});
	});
});

app.put('/changeUsername', (req, res) => {
	fs.readFile(testdata, 'utf8', (err,data) => {
		if (err) throw err;

		var all_data = JSON.parse(data);
		var ind;

		all_data.users.forEach((item, index) => {
			if (item.name == req.query.name){
				item.name = req.query.newname;
				ind = index;
			}
		})
		fs.writeFile(testdata, JSON.stringify(all_data), err => {
			if (err) throw err;
			res.end(JSON.stringify(all_data.users[ind]));
			console.log(all_data);
		});
	});
});


var server = app.listen(process.env.PORT || 3000, () => {
	console.log("Express server listening on port %d in %s mode", server.address().port, app.settings.env);
});