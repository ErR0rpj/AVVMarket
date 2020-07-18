const functions = require('firebase-functions');

// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });

exports.whenbuy = functions.database.ref('/stocks/{pushId}/buycount').onWrite((change, context) => {

	var pcntchanged = Math.floor(Math.random() * 10 + 1);
	var curprice = change.after.val();
	//var posorneg = Math.floor(Math.random() * 10 +1);

	if(pcntchanged > 7){
		pcntchanged = pcntchanged * (-1);
	}

	var valchange = (curprice * pcntchanged)/100;
	var changed = Math.floor(curprice + valchange);

	console.log("buy random function deloyed, Sir! With pcntchange = " + pcntchanged);

	return change.after.ref.parent.child('currentprice').set(changed);

});

exports.whensell = functions.database.ref('/stocks/{pushId}/sellcount').onWrite((change, context) => {

	var pcntchanged = Math.floor(Math.random() * 10 + 1);
	var curprice = change.after.val();
	//var posorneg = Math.floor(Math.random() * 10 +1);

	if(pcntchanged < 8){
		pcntchanged = pcntchanged * (-1);
	}

	var valchange = (curprice * pcntchanged)/100;
	var changed = Math.floor(curprice + valchange);

	console.log("sell random function deloyed, Sir! With pcntchange = " + pcntchanged);

	return change.after.ref.parent.child('currentprice').set(changed);

});
