$(document).ready(function(){
  $("#searchTrainingPlan").click(function(){
	  $.post("searchTrainingPlan",
	  		  { cmd:"searchTrainingPlan",
	  		    searchArea:$("#searchArea").val()
	  		  }).done(function( data, status ) {
	  			  //alert(data);
	  			  console.log("ici " + status + data.toString() + " ici");
	  			  $('#divserarch').html("flkjdsqgfiuycsgfdiubfezjnxro   :  " + data.toString());
	  			  data="";
	  		  });	 
	  	});
});
  