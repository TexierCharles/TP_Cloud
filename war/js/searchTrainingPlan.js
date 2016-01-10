$(document).ready(function() {

					var searchedValue = new RegExp('[?&]'+ encodeURIComponent('title') + '=([^&]*)').exec(location.search);
					if (searchedValue != null) {
						console.log("searchedValue" + searchedValue[1]);
						document.getElementById("searchArea").value = searchedValue[1];
						//console.log("document.getElementById('searchArea').value"+ document.getElementById("searchArea").value);

						// because the simulate click is not working
						$.post("searchTrainingPlan",{
											cmd : "searchTrainingPlan",
											searchArea : $("#searchArea").val(),
										},
										function(data, status) {
											var jsonData = jQuery.parseJSON(data);
											//console.log("parse JSON "+ jsonData.description.toString());
											document.getElementById("training").innerHTML = jsonData.title.toString()
													+ " "+ jsonData.description.toString()
													+ " "+ jsonData.domain.toString();

											if (jsonData.exercices.length > 0) {
												for (var i = 0; i < jsonData.exercices.length; i++) {
													var exoDataJSON = jQuery.parseJSON(jsonData.exercices[i]);
													//console.log("first"+ exoDataJSON.ex_title.toString());
													var stringForDiv = "exercice"+ i;
													document.getElementById(stringForDiv.toString()).innerHTML 
													= exoDataJSON.ex_title.toString()
															+ " "+ exoDataJSON.description_ex.toString()
															+ " "+ exoDataJSON.duree_ex.toString();
												}
											}
										});
					}

					$("#searchTrainingPlan").on('click',function() {
										$.post("searchTrainingPlan",{
															cmd : "searchTrainingPlan",
															searchArea : $("#searchArea").val(),
														},
														function(data, status) {
															var jsonData = jQuery.parseJSON(data);
															//console.log("parse JSON "+ jsonData.description.toString());
															document.getElementById("training").innerHTML = jsonData.title.toString()
																	+ " "+ jsonData.description.toString()
																	+ " "+ jsonData.domain.toString();

															if (jsonData.exercices.length > 0) {
																for (var i = 0; i < jsonData.exercices.length; i++) {
																	var exoDataJSON = jQuery.parseJSON(jsonData.exercices[i]);
																	//console.log("first"+ exoDataJSON.ex_title.toString());
																	var stringForDiv = "exercice"+ i;
																	document.getElementById(stringForDiv.toString()).innerHTML 
																	= exoDataJSON.ex_title.toString()
																			+ " "+ exoDataJSON.description_ex.toString()
																			+ " "+ exoDataJSON.duree_ex.toString();
																}
																exoDataJSON = "";
															}
														});
									});
				});
