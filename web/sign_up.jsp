<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Sign Up</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="sign_up.css">
	<script src="sign_up.js"></script>
</head>
<body>
<form>
	<div class="container">
		<h1>Sign Up</h1>
		<p>Please fill in this form to create an account</p>
		<hr>
		
		<div class="form-group has-feedback row">
			<div class="col-md-4">
				<label class="control-label">First Name</label><br/>
				<input type="text" class="form-control input-md" id="first-name" placeholder="Tommy" required> 
				<p class="note">Input your first name.</p>
			</div>
		</div>
		<div class="form-group has-feedback row">
			<div class="col-md-4">
				<label class="control-label">Last Name</label><br/>
				<input type="text" class="form-control input-md" id="last-name" placeholder="John" required> 
				<p class="note">Input your last name.</p>
			</div>
		</div>
		
		<div class="form-group has-feedback row">
			<div class="col-md-4">
				<label class="control-label">Username</label><br/>
				<input type="text" class="form-control input-md" id="username" placeholder="wqxhlz" required>
				<p class="note">This will be your username. You can add the name of your organization later.</p>
			</div>
		</div>	
		
		<div class="form-group has-feedback row">
			<div class="col-md-4">
				<label class="control-label">Email</label><br/>
				<input type="text" class="form-control input-md" id="email" placeholder="abc@cde.com" required>
				<p class="note">We’ll occasionally send updates about your account to this inbox. We’ll never share your email address with anyone.</p>
			</div>	
		</div>	

		<div class="form-group has-feedback row">
			<div class="col-md-4">
				<label class="control-label">Password</label><br/>
				<input type="password" class="form-control input-md" id="password" placeholder="*******" required>
				<p class="note">Use at least one lowercase letter, on uppercase, one numeral, and six characters.</p>
			</div>	
		</div>

		<div class="form-group has-feedback row">
			<div class="col-md-4">
				<label class="control-label">Confirm Password</label><br/>
				<input type="password" class="form-control input-md" id="re-password" placeholder="*******" required>
				<p class="note">Confirm the password.</p>	
			</div>
		</div>
		<div class="form-group row">
			<label>
	        	<input type="checkbox" name="accept" style="margin-bottom:15px"> I accept the <a href="#" style="color:dodgerblue">Terms & Privacy</a>
	      	</label>
      	</div>
      	<button type="button" class="btn btn-primary btn-md" name="agree" id="register">Sign Up</button>
	</div>
</form>
</body>
</html>