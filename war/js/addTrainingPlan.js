$(document).ready(function(){
  $("#addTrainingPlan").click(function(){
	  	$.post("addTrainingPlan",
    		  {
    		    cmd:"addTrainingPlan",
    		    title:$("#inputTitle").val(),
    		    description:$("#inputDescription").val(),
    		    domain:$("#inputDomain :selected").text()
    		    //exercice:{exerciceTitle:$("titleDescription").val(), exerciceDescription:$("exerciceDescription").val()}, // need to be create before and out of the POST
    		  },
    		  function(data,status){
    		  });
  		});
});