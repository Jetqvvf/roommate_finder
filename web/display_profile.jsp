<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Sign In</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/bootstrap-table.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/bootstrap-table.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/extensions/export/bootstrap-table-export.min.js"></script>
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round|Open+Sans">
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="display_profile.js"></script>
</head>
<body>
<nav class="navbar navbar-default navbar-static-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Roommate Finder</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li id="addCase.jsp"><a id="uploadli" href="profile.jsp">Edit Profile</a></li>
                <li class="active" id="preProcess.jsp"><a id="preli" href="display_profile.jsp">Roommates</a></li>
            </ul>
            <div id="known">
                <ul class="nav navbar-nav navbar-right">
                    <li id="user_name"><a href="#"><span class="glyphicon glyphicon-user"></span></a></li>
                    <li id="logout"><a href="#"><span class="glyphicon glyphicon-log-in"></span> Log out</a></li>
                </ul>
            </div>
        </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <table id="bootstrapTable" ></table>
    </div>
</div>
</body>
</html>