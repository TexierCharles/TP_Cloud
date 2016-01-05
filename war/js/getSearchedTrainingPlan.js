$(document).ready(function(){
	var searchedTrainingPlan;

	$.get("/searchTrainingPlan", function(data,status){
		searchedTrainingPlan = data;
		$('.searchedTrainingPlan').html(searchedTrainingPlan);
	}); 
	
});