$(document).ready(function() {
	var welcomeMessage;

	$.get("/getWelcomeMsg", function(data, status) {
		welcomeMessage = data;
		$('.message').html(welcomeMessage);
	});

});