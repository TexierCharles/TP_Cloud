function createTable(){
	 
	mytable = $('<table></table>').attr({ id: "basicTable" });
	var exerciceTitle = $("#titleDescription").val();
	var exerciceDescription = $("#exerciceDescription").val();
	var time1 = $("#time1").val();
	var time2 = $("#time2").val();
	var time3 = $("#time3").val();
	var tr = [];
	
	var row = $('<tr></tr>').attr({ class: ["class1", "class2", "class3"].join(' ') }).appendTo(mytable);
	$('<td></td>').text(exerciceTitle).appendTo(row); 
	$('<td></td>').text(exerciceDescription).appendTo(row);
	$('<td></td>').text(time1+":"+time2+":"+time3).appendTo(row); 

	mytable.appendTo("#box");
}