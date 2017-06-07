<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../../favicon.ico">
<title>mjunction</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/mjunction-styles.css" rel="stylesheet">
<link href="css/menu.css" rel="stylesheet">
<link rel="stylesheet" href="css/jquery.dataTables.css"/>

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

<!-- Custom styles for this template -->

<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="css/footable.metro.css" rel="stylesheet">
<link href="css/footable-demos.css" rel="stylesheet">
<link href="css/footable.core.css" rel="stylesheet">
<link rel="stylesheet" href="css/jquery.jqplot.min.css">
</head>

<body>
	<header>

		<div class="col-sm-2 LogoHolder">
			<img src="img/logo.png">
		</div>
		<div class="col-sm-6 MainHeading">
			<div class="HeadingHolder">Business Activity Monitoring
				Dashboard</div>
			<!--HeadingHolder-->
		</div>
		<!--col-sm-5-->
		<div class="col-sm-4 AccHoldr">
			<div class="AccountDetails">
				<!--<div class="col-sm-1 HomeIcon"> <a href="#"><img src="img/homeicon.png" width="21" height="22"></a></div>
     
      <div class="col-sm-1 HomeIcon"> <a href="#"><img src="img/helpicon.png" width="21" height="22"></a></div>-->

				<div class="col-sm-8 AcD">
					<div class="col-sm-10">
						<div class="AccountDetailsText">
							Welcome CEO | <a href="#"><i class="fa fa-sign-out "
								aria-hidden="true"></i></a> <br> 16.06.2015 | 15:39 PM
						</div>
						<!--AccountDetailsText-->

					</div>
					<!--col-sm-8-->
					<div class="col-sm-2">
						<div class="UserImage ">
							<img src="img/user1.jpg">
						</div>
						<!--UserImage-->
					</div>
					<!--col-sm-4-->
				</div>
				<!--col-sm-10-->
			</div>
			<!--AccountDetails-->
		</div>
		<!--col-sm-5-->

		<!--container-->
	</header>

	<!--RedAlert-->

	<div class="MainContainer">
	<input type="hidden" id="tokenValue" name="tokenValue" value="">
		<div class="container">
			<div class="col-sm-6 pull-right">
				<!-- Start Search Result Show -->
				<div class="col-sm-12 srcResultContainer">
					<div class="col-sm-4">
						<strong>From Date :</strong> 20-06-2016<br>
						<strong>To Date :</strong> 25-06-2016
					</div>
					<div class="col-sm-4 margTp5">
						<strong>Client :</strong> BSP
					</div>
					<div class="col-sm-3 text-right">
						<button type="submit" class="btn btn-default">Back to
							Search</button>
					</div>
					<div class="col-sm-1 margTp5">
						<button type="button" class="close closeTxt" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
				</div>
				<!-- End Search Result Show -->
				<div class="dropdown ExportButton pull-right">
					<div class="btn btn-primary dropdown-toggle ExportBg" type="button"
						data-toggle="dropdown">
						Export <span class="caret"></span>
					</div>
					<ul class="dropdown-menu">
						<li>Export as PDF</li>
						<li>Export as Excel</li>

					</ul>
				</div>
			</div>
			<ul class="nav nav-tabs" id="myTab">
				<li class="active"><a data-target="#home" data-toggle="tab">Dashboard</a></li>
				<li><a data-target="#profile" data-toggle="tab">Search</a></li>

			</ul>

			<div class="tab-content">
				<div class="tab-pane active" id="home">
					<div class="col-sm-7">
						<div class="upper">
							<div class="col-sm-5 NoPadL">
								<div class="AdminElementBox">

									<div class="col-sm-12 NoPad">
										<div class="col-sm-6 NoPad">
											<div class="AdminYellow AdimVio">
												<a href="#">
													<h2 class="FTop" id="totalActivities"></h2>
<!-- 													<input type="text" id="totalActivities"> -->
													<p>Total Activities</p>

												</a>
											</div>
											<!--AdminYellow-->
										</div>
										<div class="col-sm-6 NoPad">
											<div class="AdminYellow AdminRed">
												<a href="#">
													<h2 class="FTop" id="underProcessing"></h2>
<!-- 													<input type="text" id="underProcessing"> -->
													<p>Under Processing</p>


												</a>
											</div>
											<!--AdminYellow-->
										</div>
									</div>
									<div class="col-sm-12 NoPad">
										<div class="col-sm-6 NoPad">
											<div class="AdminYellow AdminBlue">
												<a href="#">
													<h2 class="FTop"  id="successfulActivities"></h2>
<!-- 													<input type="text"  id="successfulActivities"> -->
													<p>Successful Activities</p>
												</a>
											</div>
											<!--AdminYellow-->
										</div>
										<div class="col-sm-6 NoPad">
											<div class="AdminYellow ">
												<a href="#">
													<h2 class="FTop" id="failedActivities"></h2>
<!-- 													<input type="text" id="failedActivities"> -->
													<p>Failed Activities</p>
												</a>
											</div>
											<!--AdminYellow-->
										</div>
									</div>
								</div>
							</div>

							<div class="col-sm-7 NoPadR">
								<div class="PiechartHolder">
									<h3>Failed & Successful Activities</h3>
<!-- 									<img src="img/piechart.png"> -->
								<div id="pieChartFailedSuccess" class="pieChartFailedSuccess" style="height:200px;width:300px; "></div>
								</div>
								<!--PiechartHolder-->
							</div>
						</div>
						
						<div class="lower">
							<div class="PiechartHolder">
								<h3>Activity Graph</h3>
<!-- 								<img src="img/Graph1.jpg" width="515" height="290"> -->
							<div id="activity_chart"></div>
							</div>
						</div>

					</div>
					<div class="col-sm-5">
						<div class="DtTable">
							<table width="100%" border="0" cellspacing="0" cellpadding="0"  id="auction_details_table">
								<thead>
									<tr>
										<th>Client</th>
										<th>Activity</th>
										<th>Date</th>
										<th>Status</th>
									</tr>
								</thead>
<!-- 								<tbody> -->
<!-- 									<tr> -->
<!-- 										<td>BSL</td> -->
<!-- 										<td>Catalogue Data to Auction System Cat Code : -->
<!-- 											FA20001002</td> -->
<!-- 										<td>20-07-2016 10:00:00</td> -->
<!-- 										<td><i class="fa fa-check green cursorHand" -->
<!-- 											aria-hidden="true" data-toggle="modal" data-target="#myModal"></i> -->
<!-- 										</td> -->
<!-- 									</tr> -->
<!-- 									<tr> -->
<!-- 										<td>BSL</td> -->
<!-- 										<td>H1 Report to BSL Cat Code : FA20001002</td> -->
<!-- 										<td>20-07-2016 14:00:00</td> -->
<!-- 										<td><i class="fa fa-times red cursorHand" -->
<!-- 											aria-hidden="true" data-toggle="modal" data-target="#myModal"></i> -->
<!-- 										</td> -->
<!-- 									</tr> -->
<!-- 									<tr> -->
<!-- 										<td>BSP</td> -->
<!-- 										<td>Catalogue Data to Auction System Cat Code : 123456789 -->
<!-- 										</td> -->
<!-- 										<td>21-07-2016 12:00:00</td> -->
<!-- 										<td><i class="fa fa-spinner blue" aria-hidden="true"></i> -->
<!-- 										</td> -->
<!-- 									</tr> -->
<!-- 									<tr> -->
<!-- 										<td>BSL</td> -->
<!-- 										<td>Detailed Bid Report Cat Code : FA200000987</td> -->
<!-- 										<td>21-07-2016 12:00:00</td> -->
<!-- 										<td>&nbsp;</td> -->
<!-- 									</tr> -->
<!-- 									<tr> -->
<!-- 										<td>BSP</td> -->
<!-- 										<td>Material Master Details</td> -->
<!-- 										<td>21-07-2016 14:00:00</td> -->
<!-- 										<td>&nbsp;</td> -->
<!-- 									</tr> -->
<!-- 									<tr> -->
<!-- 										<td>BSP</td> -->
<!-- 										<td>Catalogue Data to Auction System Cat Code : 123456789 -->
<!-- 										</td> -->
<!-- 										<td>21-07-2016 12:00:00</td> -->
<!-- 										<td><i class="fa fa-spinner blue" aria-hidden="true"></i> -->
<!-- 										</td> -->
<!-- 									</tr> -->
<!-- 									<tr> -->
<!-- 										<td>BSL</td> -->
<!-- 										<td>Detailed Bid Report Cat Code : FA200000987</td> -->
<!-- 										<td>21-07-2016 12:00:00</td> -->
<!-- 										<td>&nbsp;</td> -->
<!-- 									</tr> -->
<!-- 									<tr> -->
<!-- 										<td>BSP</td> -->
<!-- 										<td>Material Master Details</td> -->
<!-- 										<td>21-07-2016 14:00:00</td> -->
<!-- 										<td>&nbsp;</td> -->
<!-- 									</tr> -->
<!-- 									<tr> -->
<!-- 										<td>BSP</td> -->
<!-- 										<td>Catalogue Data to Auction System Cat Code : 123456789 -->
<!-- 										</td> -->
<!-- 										<td>21-07-2016 12:00:00</td> -->
<!-- 										<td><i class="fa fa-spinner blue" aria-hidden="true"></i> -->
<!-- 										</td> -->
<!-- 									</tr> -->
<!-- 									<tr> -->
<!-- 										<td>BSL</td> -->
<!-- 										<td>Detailed Bid Report Cat Code : FA200000987</td> -->
<!-- 										<td>21-07-2016 12:00:00</td> -->
<!-- 										<td>&nbsp;</td> -->
<!-- 									</tr> -->
<!-- 									<tr> -->
<!-- 										<td>BSP</td> -->
<!-- 										<td>Material Master Details</td> -->
<!-- 										<td>21-07-2016 14:00:00</td> -->
<!-- 										<td>&nbsp;</td> -->
<!-- 									</tr> -->
<!-- 								</tbody> -->
							</table>

							<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel">
								<div class="modal-dialog" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close closeTxt"
												data-dismiss="modal" aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<h4 class="modal-title">Error Stack Trace</h4>
										</div>
										<div class="modal-body">
											<p>
												<strong>Activity :</strong> Catalogue Data to Auction System
											</p>
											<p>
												<strong>Cat Code :</strong> 123456789
											</p>
											<p>
												<strong>Failed Date :</strong> 21-07-2016 12:00:00
											</p>
											<p>
												<strong>Reason :</strong> Stage &amp; State Description
											</p>
											<form class="form-inline WidthHundred">
												<div class="form-group WidthHundred margBot20px">
													<label for="Exception">Exception Log : </label>
													<textarea id="example4" placeholder="Enter Exception Log"
														id="Exception" class="form-control textArea78"></textarea>

												</div>
											</form>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-primary">View
												Log</button>
											<button type="button" class="btn btn-primary">Export
												to Excel</button>
											<button type="button" class="btn btn-primary">Report
												Error</button>
											<button type="button" class="btn btn-primary">Re-Initiate
												Service</button>
										</div>
									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal-dialog -->
							</div>
							<!-- /.modal -->

						</div>

					</div>

				</div>
				<div class="tab-pane" id="profile">

					<div class="col-sm-3 ">
						<div class="AdminElementBox">

							<div class="col-sm-12 NoPad">
								<div class="col-sm-6 NoPad">
									<div class="AdminYellow AdimVio">
										<a href="#">
											<h2 class="FTop" ></h2>
											<p>Total Activities</p>

										</a>
									</div>
									<!--AdminYellow-->
								</div>
								<div class="col-sm-6 NoPad">
									<div class="AdminYellow AdminRed">
										<a href="#">
											<h2 class="FTop" ></h2>

											<p>Under Processing</p>


										</a>
									</div>
									<!--AdminYellow-->
								</div>
							</div>
							<div class="col-sm-12 NoPad">
								<div class="col-sm-6 NoPad">
									<div class="AdminYellow AdminBlue">
										<a href="#">
											<h2 class="FTop"></h2>

											<p>Successful Activities</p>
										</a>
									</div>
									<!--AdminYellow-->
								</div>
								<div class="col-sm-6 NoPad">
									<div class="AdminYellow ">
										<a href="#">
											<h2 class="FTop" ></h2>
											<p>Failed Activities</p>
										</a>
									</div>
									<!--AdminYellow-->
								</div>
							</div>
						</div>
					</div>


					<div class="col-sm-9">
						<div class="PiechartHolder">
							<h3>Search Here</h3>
							<div class="col-sm-3 NoPad">
								<label>From Date</label>
								<div class="hero-unit">
									<input type="text" placeholder="Click to show datepicker"
										id="example1">
								</div>
							</div>

							<div class="col-sm-3 NoPad">
								<label>To Date</label>
								<div class="hero-unit">
									<input type="text" placeholder="Click to show datepicker"
										id="example2">
								</div>
							</div>


							<div class="col-sm-3 NoPad">
								<label>Client System</label>
								<div class="hero-unit">
									<input type="text" placeholder="Enter client systen"
										id="example3">
								</div>
							</div>
							<div class="col-sm-12 NoPad margTp35">
								<button type="button" class="btn btn-primary blueBtn">Submit</button>
								<button type="button" class="btn btn-default blueBtn1">Reset</button>
							</div>
						</div>
					</div>


				</div>

			</div>

		</div>





	</div>

	</div>
	<!--MainContainer-->
	
	<footer> Powered by TCG-Digital </footer>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/docs.min.js"></script>
	
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->



	<!-----------------------------menu------------------------------->
	<script src="js/bootstrap-datepicker.js"></script>
	
	<script src="js/custom/dashboard.js"></script>
	<script src="js/jquery.dataTables.js"></script>
	<!-- for graph ploting -->
	
	<script type="text/javascript" src="js/jquery.jqplot.min.js"></script>
	<script type="text/javascript" src="js/jqplot.bubbleRenderer.min.js"></script>
	<script type="text/javascript" src="js/jqplot.json2.min.js"></script>
	<script type="text/javascript" src="js/ts-jqplot-script.js"></script>
	<script type="text/javascript" src="js/jqplot.canvasAxisLabelRenderer.min.js"></script>
	<script type="text/javascript" src="js/jqplot.categoryAxisRenderer.min.js"></script>
	<script type="text/javascript" src="js/jqplot.pieRenderer.js"></script>
	<script type="text/javascript" src="js/jqplot.donutRenderer.js"></script>
	
	
	
	<script type="text/javascript">
            // When the document is ready
            $(document).ready(function () {
                
                $('#example1,#example2').datepicker({
                    format: "dd/mm/yyyy"
                });  
            
            });
        </script>

	<!-----------------------------menu------------------------------->


</body>
</html>
