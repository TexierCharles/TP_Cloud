$(document).ready(function(){
  $("#searchTrainingPlan1").click(function(){
		alert("add training plan");
	  	$.post("searchTrainingPlan1",
    		  { cmd:"searchTrainingPlan",
    		    searchArea:$("#searchArea").val(),
    		  },
    		  function(data,status){
    		    alert("searchTrainingPlan : searchArea is : " + data.searchArea);
    		  });
  		});
});