$(document)
		.ready(
				function() {

					var jsonData1 = {
						"PriceTicks" : [ {
							"Price" : 5.5,
							"TickDate" : "Jan"
						}, {
							"Price" : 6.8,
							"TickDate" : "Mar"
						}, {
							"Price" : 1.9,
							"TickDate" : "June"
						}, {
							"Price" : 7.1,
							"TickDate" : "Dec"
						} ]
					};
					var jsonData2 = {
						"PriceTicks" : [ {
							"Price" : 1.5,
							"TickDate" : "Jan"
						}, {
							"Price" : 2.8,
							"TickDate" : "Feb"
						}, {
							"Price" : 3.9,
							"TickDate" : "Apr"
						}, {
							"Price" : 9.1,
							"TickDate" : "Dec"
						} ]
					};

					$
							.ajax({
								type : 'GET',
								url : 'activityGraphServlet',

								success : function(data) {

									arSeries = data;

									// initalizat the output array for jqPlot
									arJQ = [];

									// for each Data Series
									for (var z = 0; z < arSeries.length; z++) {
										// array for the prices
										jqPrices = [];
										// alert("arSeries :"+arSeries.length);
										var prices = arSeries[z].PriceTicks;
										// alert("Prices : " + prices);
										for (var i = 0; i < prices.length; i++) {
											// jqPrices.push([
											// prices[i].TickDate.toString(),
											// prices[i].Price ]);
											jqPrices.push([
													prices[i].allDates
															.toString(),
													prices[i].noOfActivities ]);

										}
										// Result
										// [["Jan",1.5],["Feb",2.8],["Apr",3.9],["Dec",9.1]]
										// add to series array
										arJQ.push(jqPrices);
										// alert("Inside loop :" + arJQ);
									}

									// alert(jqPrices);

									/*
									 * Result [
									 * [["Jan",5.5],["Mar",6.8],["June",1.9],["Dec",7.1]],
									 * [["Jan",1.5],["Feb",2.8],["Apr",3.9],["Dec",9.1]] ]
									 */
									// plot and use the generated array
								//	alert(arJQ);
									$
											.jqplot(
													'activity_chart',
													arJQ,
													{
														title : 'Activity Trends',
														fontFamily : '"Arial',
														fontSize : '10pt',
														textColor : '#000000',

														seriesDefaults : {
															rendererOptions : {
																smooth : true
															}
														},
														// Series options are
														// specified as an array
														// of objects,
														// one object
														// for each series.
														series : [
																{

																	lineWidth : 2,
																	color : '#80D855',// success
																	markerOptions : {
																		style : "circle",
																		size : 6
																	}
																},
																{

																	lineWidth : 2,
																	color : '#CC0000',
																	markerOptions : {
																		style : "x", // fail
																		size : 6
																	}
																},
																{
																	lineWidth : 2,
																	color : '#DD964A',
																	markerOptions : {
																		style : "filledSquare",// under
																		size : 6
																	}
																}

														],
														axes : {
															xaxis : {
																//renderer : $.jqplot.CategoryAxisRenderer,
																renderer:$.jqplot.DateAxisRenderer, 
																rendererOptions: { tickRenderer: $.jqplot.CanvasAxisTickRenderer },
																 tickOptions: { formatString: '%Y %m %d' },
															     tickInterval: '1 day',
																label : "Last 5 Dates",
																// renderer :
																// $.jqplot.DateAxisRenderer,
//																tickOptions : {
//																	formatString : '%Y %m %d'
//																	
//																},
																

															},
															yaxis : {
																label : "Activities",
																fontSize : '6pt',
															}

														// xaxis:{
														// renderer:$.jqplot.DateAxisRenderer,
														// tickOptions:{
														// formatString:'%b&nbsp;%#d'
														// }
														// },
														// yaxis:{
														// min:-1,
														// tickOptions:{
														// formatString:'%d'
														// }
														// }

														}

													});
								}
							});

					// $.ajax({
					// type : 'GET',
					// url : 'activityPieServlet',
					//
					// success : function(data) {
					// //alert(data);
					// var plot = $.jqplot('pieChartFailedSuccess', data , {
					//
					// title : {
					// text : 'Failed & Successful Activities',
					// fontFamily : '"Arial',
					// fontSize : '11pt',
					// textColor : '#000000'
					// },
					// // title: 'Failed & Successful Activities',
					// seriesDefaults : {
					// renderer : $.jqplot.PieRenderer,
					// rendererOptions : {
					// showDataLabels : true,
					// padding : 10,
					// sliceMargin : 2,
					// shadow : false
					// }
					// },
					// legend : {
					// renderer : $.jqplot.EnhancedLegendRenderer,
					// rendererOptions : {
					// numberColumns : 3
					// },
					// show : true
					// },
					// highlighter : {
					// show : true,
					// useAxesFormatters : false,
					// tooltipFormatString : '%s'
					// }
					//
					// });
					// }
					// });

				//	var line =[[['Successful Activity',6],['Failed Activity',4],['Under Processing Activity',59]]];

					$
							.ajax({
								type : 'GET',
								url : 'activityPieServlet',

								success : function(data) {
									
									var plot = $
											.jqplot(
													'pieChartFailedSuccess', [data] ,	{

														title : {
															text : 'Failed,Under Processing & Successful Activities',
															fontFamily : '"Arial',
															fontSize : '9pt',
															textColor : '#000000'
														},
														// title: 'Failed &
														// Successful
														// Activities',
														seriesDefaults : {
															renderer : $.jqplot.PieRenderer,
															rendererOptions : {
																showDataLabels : true,
																padding : 10,
																sliceMargin : 6,
																shadow : false
															}
														},
														legend: { show: true, location: 'e' },
//														legend : {
//															renderer : $.jqplot.EnhancedLegendRenderer,
//															rendererOptions : {
//																numberColumns : 3
//															},
//															show : true
//														},
														highlighter : {
															show : true,
															useAxesFormatters : false,
															tooltipFormatString : '%s'
														}

													});
								}
							});
				});