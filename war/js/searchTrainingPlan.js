$(document).ready(function(){
	
	var searchedValue = new RegExp('[?&]'+encodeURIComponent('title')+'=([^&]*)').exec(location.search);
	if(searchedValue != "")
	{
		console.log("searchedValue" + searchedValue[1]);
		document.getElementById("searchArea").value = searchedValue[1];
		console.log("document.getElementById('searchArea').value" + document.getElementById("searchArea").value);
		
		// because the simulate click is not working
		$.post("searchTrainingPlan",
	       		  { cmd:"searchTrainingPlan",
	       		    searchArea:$("#searchArea").val(),
	       		  },
	       		  function(data,status){
	       			//alert(data);		
	       			 	  			  console.log("ici " + status + data.toString() + " ici");
	       			 	  			  $('#divserarch').html("flkjdsqgfiuycsgfdiubfezjnxro   :  " + data.toString());
	       			 	  			  
	       			 	  			  console.log("ici " + status + data.toString());
	       			 	  			  $('#training').html("flkjdsqgfiuycsgfdiubfezjnxro   :  " + data.toString());
	       			 	  			console.log("ici " + status + data.toString());
	       			 	  			document.getElementById("training").innerHTML = data;
	       		  });
	}
	
	
	   $("#searchTrainingPlan").on('click',function(){
		   	  	$.post("searchTrainingPlan",
		       		  { cmd:"searchTrainingPlan",
		       		    searchArea:$("#searchArea").val(),
		       		  },
		       		  function(data,status){
		       			//alert(data);		
		       			 	  			  console.log("ici " + status + data.toString() + " ici");
		       			 	  			  $('#divserarch').html("flkjdsqgfiuycsgfdiubfezjnxro   :  " + data.toString());
		       			 	  			  
		       			 	  			  console.log("ici " + status + data.toString());
		       			 	  			  $('#training').html("flkjdsqgfiuycsgfdiubfezjnxro   :  " + data.toString());
		       			 	  			console.log("ici " + status + data.toString());
		       			 	  			document.getElementById("training").innerHTML = data;
		       		  });
		     		});
});
