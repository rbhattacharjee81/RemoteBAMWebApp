$(document)
		.ready(
				function() {

					//$('#logout').hide();

					$
							.ajax({

								type : 'POST',
								url : 'prepopulation',

								data : {
									tokenValue : $('#tokenValue').val()

								},
								success : function(data) {
									// alert(data.failedActivities);
									$('#totalActivities').html(
											data.totalActivities);
									$('#underProcessing').html(
											data.underProcessing);
									$('#successfulActivities').html(
											data.successfulActivities);
									$('#failedActivities').html(
											data.failedActivities);
									
									var populateDatatable = function() {
										$('#auction_details_table')
												.dataTable(
														{
															"bProcessing" : false,
															"bAutoWidth" : false,
															"bDestroy" : true,
															"sAjaxSource" : 'populateParticularOverAllActivity',
															"iDisplayLength" : 30,
															"bJQueryUI" : false,
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
																	},

															],
															"fnServerParams" : function(
																	aoData) {
																aoData
																		.push({
																			"name" : "tokenValue",
																			"value" : $(
																					'#tokenValue')
																					.val()
																		});
															}
														});

									};
									populateDatatable();

								},
								error : function(data) {

									if (data.status === 402) {
										$('#loginFailResponse')
												.text(
														"Username or Password is incorrect");
									}
								}

							});

					$('#totalActivities')
							.click(
									function(data) {
										// alert($('#tokenValue').val());
										var populateDatatable = function() {
											$('#auction_details_table')
													.dataTable(
															{
																"bProcessing" : false,
																"bAutoWidth" : false,
																"bDestroy" : true,
																"sAjaxSource" : 'populateParticularTotalActivity',
																"bJQueryUI" : true,
																"iDisplayLength" : 30,
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
																		},

																],
																"fnServerParams" : function(
																		aoData) {
																	aoData
																			.push({
																				"name" : "tokenValue",
																				"value" : $(
																						'#tokenValue')
																						.val()
																			});
																}
															});

										};
										populateDatatable();

									});

					$('#underProcessing')
							.click(
									function(data) {
										// alert($('#tokenValue').val());
										var populateDatatable = function() {
											$('#auction_details_table')
													.dataTable(
															{
																"bProcessing" : false,
																"bAutoWidth" : false,
																"bDestroy" : true,
																"sAjaxSource" : 'populateParticularUnderActivity',
																"bJQueryUI" : true,
																"iDisplayLength" : 30,
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
																		},

																],
																"fnServerParams" : function(
																		aoData) {
																	aoData
																			.push({
																				"name" : "tokenValue",
																				"value" : $(
																						'#tokenValue')
																						.val()
																			});
																}
															});

										};
										populateDatatable();

									});

					$('#successfulActivities')
							.click(
									function(data) {
										// alert($('#tokenValue').val());
										var populateDatatable = function() {
											$('#auction_details_table')
													.dataTable(
															{
																"bProcessing" : false,
																"bAutoWidth" : false,
																"bDestroy" : true,
																"sAjaxSource" : 'populateParticularSuccessfulActivity',
																"bJQueryUI" : true,
																"iDisplayLength" : 30,
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
																		},

																],
																"fnServerParams" : function(
																		aoData) {
																	aoData
																			.push({
																				"name" : "tokenValue",
																				"value" : $(
																						'#tokenValue')
																						.val()
																			});
																}
															});

										};
										populateDatatable();

									});

					$('#failedActivities')
							.click(
									function(data) {
										// alert($('#tokenValue').val());
										var populateDatatable = function() {
											$('#auction_details_table')
													.dataTable(
															{
																"bProcessing" : false,
																"bAutoWidth" : false,
																"bDestroy" : true,
																"sAjaxSource" : 'populateParticularFailedActivity',
																"bJQueryUI" : true,
																"iDisplayLength" : 30,
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
																		},

																],
																"fnServerParams" : function(
																		aoData) {
																	aoData
																			.push({
																				"name" : "tokenValue",
																				"value" : $(
																						'#tokenValue')
																						.val()
																			});
																}
															});

										};
										populateDatatable();

									});

					$('#AccountDetailsText').click(function(data) {

						$('#logout').show();

					});

					$('#logout').click(function(data) {

						$.ajax({
							type : 'POST',
							url : 'logout',

							success : function(data) {
								var url = "login.jsp";

								url.slice(0, url.indexOf('?'));

								window.location.href = url;

							},
							error : function(data) {

							}

						});

					});

					var oTable8;
					oTable8 = $('#auction_details_table').dataTable();

					$('#auction_details_table tbody').delegate("tr", "click",
							rowClick);
					var hlr8 = 0; // Reference to the currently highlighted
					// row
					var client = 0, activity, dateLoc = 0, status, values, catCode = 0, realActivity = 0
					row8 = $("td:first", this).parent().children();
					function rowClick() {

						if (hlr8)
							$("td:first", hlr8).parent().children().each(
									function() {
										$(this).removeClass('markrow');
									});
						hlr8 = this;
						$("td:first", this).parent().children().each(
								function() {
									$(this).addClass('markrow');
								});
						// You can pull the values out of the row here if
						// required
						client = $("td:first", this).text();
						activity = $("td:eq(1)", this).text();
						dateLoc = $("td:eq(2)", this).text();
						status = $("td:eq(3)", this).text();

						values = activity.split(':');

						catCode = values[1];
						realActivity = values[0];

						populateModalData();

					}

					function populateModalData() {
						//alert("Hi");
						$.ajax({

							type : 'POST',
							url : 'errorStack',

							data : {
								tokenValue : $('#tokenValue').val(),
								catCode : catCode,
								timeStamp : dateLoc
							},

							success : function(data) {
								
														
								jQuery.each( data, function( key, value ) {
									
									//alert(key);
									//alert(value);
									$('#reason').val(key);
									$('#exceptionLog').val(value);
								});
								$('#client').val(client);
								$('#activity').val(realActivity);
								$('#catCode').val(catCode);
								$('#failedDate').val(dateLoc);
								$('#myModal').modal('show');
							},
							error : function(data) {

							}

						});
					}

				});