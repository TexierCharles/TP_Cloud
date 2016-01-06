$(document).ready(function(){
  $("#searchTrainingPlan").click(function(){
	  $.post("searchTrainingPlan",
	  		  { cmd:"searchTrainingPlan",
	  		    searchArea:$("#searchArea").val()
	  		  }).done(function( data, status ) {
	  			  //alert(data);
	  			  console.log("ici " + status + data.toString());
	  			  $('#training').html("flkjdsqgfiuycsgfdiubfezjnxro   :  " + data.toString());
	  			console.log("ici " + status + data.toString());
	  			document.getElementById("training").innerHTML = data;
	  		  });	 
	  	});
});