$(document).ready(function(){
	var welcomeMessage;

	$.get("/getWelcomeMsg", function(data,status){
		alert(data);
		welcomeMessage = data;
		$('.message').html(welcomeMessage);
	}); 
	
});