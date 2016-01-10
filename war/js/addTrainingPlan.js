$(document).ready(function(){
	var exercices = new Array();
	var i = 0;
	 $("#addexButton").prop('disabled',true);
	    $("#inputTitle").keyup(function(){
	        $("#addexButton").prop('disabled', this.value == "" ? true : false);     
	    })
	 $("#addexButton").click(function(){
			
		 	mytable = $('<table></table>').attr({ id: "basicTable" });
			var exerciceTitle = $("#titleDescription").val();
			var exerciceDescription = $("#exerciceDescription").val();
			var time1 = $("#time1").val();
			var time2 = $("#time2").val();
			var time3 = $("#time3").val();
			var time = time1+"h"+time2+"m"+time3+"s";
			
			
			var row = $('<tr></tr>').attr({ class: ["class1", "class2", "class3"].join(' ') }).appendTo(mytable);
			$('<td></td>').text(exerciceTitle).appendTo(row); 
			$('<td></td>').text(exerciceDescription).appendTo(row);
			$('<td></td>').text(time1+"h"+time2+"m"+time3+"s").appendTo(row); 
			
			var position = i+1;

			mytable.appendTo("#box");
			var myexercice = {"title":exerciceTitle, "description":exerciceDescription, "duree":time, "position":position };
			
			exercices[i] = myexercice;
			console.log(exercices);
			i++;
			
	 });
	    
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
	  	$.post("addExercice", 
	  			{
	  		cmd:"addExercice",
		    title:$("#inputTitle").val(),
		    exercices:JSON.stringify(exercices),
		    //exercice:{exerciceTitle:$("titleDescription").val(), exerciceDescription:$("exerciceDescription").val()}, // need to be create before and out of the POST
		  },
		  function(data,status){
		  }, "json");
	
	  	window.location.href = "ha-search-screen.html";
  		}); 
});
