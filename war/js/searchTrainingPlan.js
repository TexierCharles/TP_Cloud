$(document).ready(function(){
  $("#searchTrainingPlan").click(function(){
	  	$.post("searchTrainingPlan",
    		  { cmd:"searchTrainingPlan",
    		    searchArea:$("#searchArea").val(),
    		  },
    		  function(data,status){
    		  });
  		});
});