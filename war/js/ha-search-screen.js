var googleUser = {};
var startBtn = function() {
	document.getElementById('name').style.display = 'none';
	gapi.load('auth2',function() {
						// Retrieve the singleton for the GoogleAuth library and set up the client.
						auth2 = gapi.auth2.init({
									client_id : '804387353700-bb6hdfki7lni9jmo82jsqruunbom89n7.apps.googleusercontent.com',
									cookiepolicy : 'single_host_origin',
								// Request scopes in addition to 'profile' and 'email'
								//scope: 'additional_scope'
								});
						attachSignin(document.getElementById('signinButton'));
					});
};

function attachSignin(element) {
	//console.log(element.id);
	auth2.attachClickHandler(element, {}, function(googleUser) {
		document.getElementById('signinButton').style.display = 'none';
		document.getElementById('name').style.display = 'inline-block';
		document.getElementById('name').innerText = googleUser
				.getBasicProfile().getName()
				+ "\n" + googleUser.getBasicProfile().getEmail();
	}, function(error) {
		alert(JSON.stringify(error, undefined, 2));
	});
}