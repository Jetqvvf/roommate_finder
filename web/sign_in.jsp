<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Sign In</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="sign_in.js"></script>
</head>
<body>
<form>
	<div class="container">
		<h1>Sign In</h1>
		<p>Please fill in this form to login</p>
		<hr>

		
		<div class="form-group row">
			<div class="col-md-4">
				<label class="control-label">Username</label><br/>
				<input type="text" class="form-control input-md" id="username" required>
			</div>
		</div>	

		<div class="form-group row">
			<div class="col-md-4">
				<label class="control-label">Password</label><br/>
				<input type="password" class="form-control input-md" id="password" required>
			</div>	
		</div>

      	<button type="button" class="btn btn-primary btn-md" name="agree" id="login">Sign In</button>
	</div>
</form>
</body>
</html>