<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Sign In</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="../static/jquery.min.js"></script>
	<script src="profile.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Roommate Finder</a>
        </div>
<div>
            <ul class="nav navbar-nav">
                <li class="active" id="addCase.jsp"><a id="uploadli" href="profile.jsp">Edit Profile</a></li>
                <li id="preProcess.jsp"><a id="preli" href="../display_user.jsp">Roommates</a></li>
            </ul>
       
            <div id="known">
                <ul class="nav navbar-nav navbar-right">
                    <li id="user_name"><a href="#"><span class="glyphicon glyphicon-user"></span></a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Log out</a></li>
                </ul>
            </div>
        </div>
        </div>
<form>
	<div class="container">
		<h1>Edit Profile</h1>
		<p>Please fill in this form to edit the profile</p>
		<hr>
	
		<div class="form-group row">
			<div class="col-md-4">
				<label class="control-label">Phone number:</label><br/>
				<input type="text" class="form-control input-md" id="phone-number" required>
			</div>
		</div>	

		<div class="form-group row">
			<div class="col-md-4">
				<label class="control-label">Address:</label><br/>
				<input type="text" class="form-control input-md" id="address" required>
			</div>	
		</div>
		
		<div class="form-group row">
			<div class="col-md-4">
				<label class="control-label">City:</label><br/>
				<input type="text" class="form-control input-md" id="city" required>
			</div>	
		</div>
		
		<div class="form-group row">
			<div class="col-md-4">
				<label class="control-label">State:</label><br/>
				<input type="text" class="form-control input-md" id="state" required>
			</div>	
		</div>
		
		<div class="form-group row">
			<div class="col-md-4">
				<label class="control-label">Zip Code:</label><br/>
				<input type="text" class="form-control input-md" id="zip-code" required>
			</div>	
		</div>
		
		<div class="form-group row">
			<div class="col-md-4">
				<label class="control-label">Language:</label><br/>
				<input type="text" class="form-control input-md" id="language" required>
			</div>	
		</div>
		
		<div class="form-group row">
			<div class="col-md-4">
				<label class="control-label">Roommate Number:</label><br/>
				<input type="text" class="form-control input-md" id="roommate-number" required>
			</div>	
		</div>
		
		<div class="form-group row">
			<div class="col-md-4">
				<label class="control-label">Department:</label><br/>
				<input type="text" class="form-control input-md" id="department" required>
			</div>	
		</div>
		
		<div class="form-group row">
			<div class="col-md-4">
				<label class="control-label">Track:</label><br/>
				<input type="text" class="form-control input-md" id="track" required>
			</div>	
		</div>
		
		<div class="form-check row">
			<input class="form-check-input" type="checkbox" value="" id="cooking">
			  <label class="form-check-label" for="non-veg">Cooking:</label>
		</div>
		
		<div class="form-check row">
			<input class="form-check-input" type="checkbox" value="" id="non-veg">
			  <label class="form-check-label" for="defaultCheck1">Non-veg:</label>
		</div>
		
		<div class="form-check row">
			<input class="form-check-input" type="checkbox" value="" id="smoking">
			  <label class="form-check-label" for="defaultCheck1">Smoking:</label>
		</div>
		<div class="form-check row">
			<input class="form-check-input" type="checkbox" value="" id="drinking">
			  <label class="form-check-label" for="defaultCheck1">Drinking:</label>
		</div>
		<div class="form-check row">
			<input class="form-check-input" type="checkbox" value="" id="pets">
			  <label class="form-check-label" for="defaultCheck1">Pets:</label>
		</div>
      	<button type="button" class="btn btn-primary btn-md" name="agree" id="profile-save">Save</button>
	</div>
</form>
</body>
</html>