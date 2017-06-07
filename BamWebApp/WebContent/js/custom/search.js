$(document)
		.ready(
				function() {
					$('#dynaContent').hide();
					$("#clientName").select2();
					$('#logout').hide();
					$.ajax({
						type : 'GET',
						url : 'clientName',
						success : function(data) {
							var select = $('#clientName');
							select.find('option').remove();
							$.each(data, function(index, value) {
								$('<option>').val(value).text(value).appendTo(
										select);
							});
						},
						error : function(data) {
						}
					});
					$('#submitButton').click(function() {
										$('#dynaContent').show();
										var fromDate = $('#fromDate').val();
										var toDate = $('#toDate').val();
										$('#toDate1').val(fromDate);
										$('#fromDate1').val(toDate);
//										alert($('#toDate1').val()
//												+ $('#fromDate1').val());
										var clientName = $('#clientName').val();
										var tokenValue = $('#tokenValue').val();
//										alert(fromDate + toDate + clientName
//												+ tokenValue);
										if(fromDate!=null&&toDate!=null&&clientName!=null){
											$.ajax({

												type : 'POST',
												url : 'badgePopulation',

												data : {
													tokenValue : $(
															'#tokenValue')
															.val(),
													clientName : $(
															'#clientName')
															.val(),
													fromDate : $(
															'#fromDate')
															.val(),
													toDate : $('#toDate')
															.val()

												},
												success : function(data) {
													alert(data.failedActivities);
													$(
															'#totalActivitiesSearch')
															.html(
																	data.totalActivities);
													$(
															'#underProcessingSearch')
															.html(
																	data.underProcessing);
													$(
															'#successfulActivitiesSearch')
															.html(
																	data.successfulActivities);
													$(
															'#failedActivitiesSearch')
															.html(
																	data.failedActivities);
													var populateDatatableClientSpc = function() {
														$(
																'#auction_details_table_client_spc')
																.dataTable(
																		{
																			"bProcessing" : false,
																			"bAutoWidth" : false,
																			"bDestroy" : true,
																			"serverSide" : true,
																			"sAjaxSource" : 'populateClientSpcParticularOverAllActivity',
																			// "ajax"
																			// : {
																			// "url"
																			// :
																			// 'populateClientSpcParticularOverAllActivity',
																			// "data"
																			// : {
																			// tokenValue
																			// : $(
																			// '#tokenValue')
																			// .val(),
																			// fromDate
																			// : $(
																			// '#fromDate')
																			// .val(),
																			// toDate
																			// : $(
																			// '#toDate')
																			// .val()
																			// }
																			// },

																			"iDisplayLength" : 30,
																			"bJQueryUI" : false,
																			"fixedHeader" : true,
																			"aoColumns" : [
																					{
																						"mData" : "client"
																					},
																					{
																						"mData" : "activity"
																					},
																					{
																						"mData" : "recordDate"
																					},
																					{
																						"mData" : "status",
																						"mRender" : function(
																								data,
																								type,
																								row) {
																							return '<img src="'
																									+ data
																									+ '" />';
																						}
																					}, ],

																			"fnServerParams" : function(
																					aoData) {
																				aoData
																						.push(
																								{
																									"name" : "tokenValue",
																									"value" : $(
																											'#tokenValue')
																											.val()
																								},
																								{
																									"name" : "clientName",
																									"value" : $(
																											'#clientName')
																											.val()
																								},
																								{
																									"name" : "fromDate",
																									"value" : $(
																											'#fromDate')
																											.val()
																								},
																								{
																									"name" : "toDate",
																									"value" : $(
																											'#toDate')
																											.val()
																								});
																			},

																		});

													}
													populateDatatableClientSpc();
												},
												error : function(data) {

													if (data.status === 402) {
														$(
																'#loginFailResponse')
																.text(
																		"Username or Password is incorrect");
													}
												}

											});
									$
											.ajax({
												type : 'GET',
												url : 'activityPieServletClientSpc',
												data : {
													tokenValue : $(
															'#tokenValue')
															.val(),
													clientName : $(
															'#clientName')
															.val()
												},
												success : function(data) {
													var plot = $
															.jqplot(
																	'pieChartFailedSuccess_client_spc',
																	[ data ],
																	{
																		title : {
																			text : 'Failed,Under Processing & Successful Activities',
																			fontFamily : '"Arial',
																			fontSize : '9pt',
																			textColor : '#000000'
																		},
																		// title:
																		// 'Failed
																		// &
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
																		legend : {
																			show : true,
																			location : 'e'
																		},
																		highlighter : {
																			show : true,
																			useAxesFormatters : false,
																			tooltipFormatString : '%s'
																		}
																	});
												}
											});
									$
											.ajax({
												type : 'GET',
												url : 'activityGraphServletClientSpc',
												data : {
													tokenValue : $(
															'#tokenValue')
															.val(),
													clientName : $(
															'#clientName')
															.val()
												},
												success : function(data) {
													arSeries = data;
													// initalizat the output
													// array for jqPlot
													arJQ = [];
													// for each Data Series
													for (var z = 0; z < arSeries.length; z++) {
														// array for the
														// prices
														jqPrices = [];
														// alert("arSeries
														// :"+arSeries.length);
														var prices = arSeries[z].PriceTicks;
														// alert("Prices : "
														// + prices);
														for (var i = 0; i < prices.length; i++) {
															// jqPrices.push([
															// prices[i].TickDate.toString(),
															// prices[i].Price
															// ]);
															jqPrices
																	.push([
																			prices[i].allDates
																					.toString(),
																			prices[i].noOfActivities ]);
														}
														// Result
														// [["Jan",1.5],["Feb",2.8],["Apr",3.9],["Dec",9.1]]
														// add to series
														// array
														arJQ.push(jqPrices);
														// alert("Inside
														// loop :" + arJQ);
													}
													// alert(jqPrices);
													/*
													 * Result [
													 * [["Jan",5.5],["Mar",6.8],["June",1.9],["Dec",7.1]],
													 * [["Jan",1.5],["Feb",2.8],["Apr",3.9],["Dec",9.1]] ]
													 */
													// plot and use the
													// generated array
													// alert(arJQ);
													$
															.jqplot(
																	'activity_chart_client_spc',
																	arJQ,
																	{
																		title : 'Under Processing,Successful,Failed Activity Trends',
																		fontFamily : '"Arial',
																		fontSize : '10pt',
																		textColor : '#000000',
																		seriesDefaults : {
																			rendererOptions : {
																				smooth : true
																			}
																		},
																		series : [
																				{
																					lineWidth : 2,
																					color : '#80D855', // success
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
																						style : "filledSquare", // under
																						size : 6
																					}
																				} ],
																		axes : {
																			xaxis : {
																				// renderer
																				// :
																				// $.jqplot.CategoryAxisRenderer,
																				renderer : $.jqplot.DateAxisRenderer,
																				rendererOptions : {
																					tickRenderer : $.jqplot.CanvasAxisTickRenderer
																				},
																				tickOptions : {
																					formatString : '%Y %m %d'
																				},
																				tickInterval : '1 day',
																				label : "Last 5 Dates",
																			},
																			yaxis : {
																				label : "Activities",
																				fontSize : '6pt',
																			}
																		}
																	});
												}
											});
								
										}
										else{
											alert("");
										}

					});			
				});
