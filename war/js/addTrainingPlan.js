$(document).ready(function(){
  $("#addTrainingPlan").click(function(){
		alert("add training plan");
	  	$.post("addTrainingPlan",
    		  {
    		    cmd:"addTrainingPlan",
    		    title:$("#inputTitle").val(),
    		    description:$("#inputDescription").val(),
    		    domain:$("#e1").val,
    		    //exercice:{exerciceTitle:$("titleDescription").val(), exerciceDescription:$("exerciceDescription").val()}, // need to be create before and out of the POST
    		  },
    		  function(data,status){
    		    alert("Post Done new trainingplan , id: " + data.userid + "\nStatus: " + status);
    		  });
  		});
});