<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../../favicon.ico">
<title>mjunction Login</title>


<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/mjunction-styles.css" rel="stylesheet">

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

</head>
<body>
	<div class="LoginBg">
		<div class="text-center FTop">
			<img src="img/BamLogo.png" alt="">
		</div>
		<div class="LoginHolder">
			<div class="LoginHolderInner">
				<div class="LoginLogoHolder">
					<img src="img/logoLogin.png">
				</div>
				<!--LoginLogoHolder-->
				<form method="post" id="loginForm" class="loginForm">
				<input type="hidden" id="tokenValueLog" name="tokenValueLog" value="">
				
					<div class="LoginFields">
						<div class="LoginFieldsBlock">
							<div class="col-sm-2">
								<img src="img/user12.png" width="19" height="19">
							</div>
							<div class="col-sm-10">
								<input name="userName" id="userName" type="text"
									value="Username">
							</div>
						</div>
						<div class="LoginFieldsBlock">
							<div class="col-sm-2">
								<img src="img/lock.png" width="21" height="21">
							</div>
							<div class="col-sm-10">
								<input name="password" id="password" type="password"
									value="Password">
							</div>
						</div>
						<br>
						<div style="color: red" id="loginFailResponse"></div>
					</div>
				</form>
				<div class="LoginFieldsButton">
					<div class="col-sm-12">
						<input name="loginButton" id="loginButton" type="submit"
							value="Login">
					</div>

				</div>
				<!--LoginFields-->
				<div class="CancelFieldsButton">
					<div class="col-sm-12">
						<input name="" type="button" value="Cancel">
					</div>
				</div>
			</div>
			<!--LoginHolderInner-->
		</div>
		<!--LoginHolder-->
	</div>
	<!--LoginBg-->




	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/docs.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="js/ie10-viewport-bug-workaround.js"></script>
	<script src="js/custom/login.js"></script>
</body>
</html>
