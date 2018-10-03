<head>
    <title>HomePage</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="static/jquery.min.js"></script>
    <script>
    $(document).ready(function() {
    	
    	function setErrorMsg(id, errormsg) {
    		$("p").eq(id).css("color", "red").text(errormsg);
    	}
    	
    	$('#login').on("click", function() { 
    		var username = $('#username').val();
    		var password = $('#password').val();
    		
    		
    		if ("" == username || "" == password) {
    			setErrorMsg(0, "The username or password is empty");
    		}
            alert("send");
    		$.ajax({
    			type : 'post',
    			url : 'LoginControl',
    			data :JSON.stringify({
    				username : username,
    				password : password,
    				submit : "login"
    			}),
    			contentType: "application/json; charset=utf-8",
    			dataType : 'json',
    			
    			success: function(response) {
    			    alert(response.index);
    				if (response.error == 0) {
    					//modified by Qiao Wang @20180920
    					location.href = response.index;
    				} else {
    					setErrorMsg(0, "username or password is invalid");
    				} 
    			},
    			error: function() {
    			}
    		});
    	})
    });
	
    </script>
</head>
<body>
<nav class="navbar navbar-default navbar-static-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Roommate Finder</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li id="addCase.jsp"><a id="uploadli" href="#">Func1</a></li>
                <li id="preProcess.jsp"><a id="preli" href="#">Func2</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        Func3 <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Func3.1</a></li>
                        <li><a href="#">Func3.2</a></li>
                        <li><a href="#">Func3.3</a></li>
                    </ul>
                </li>
            </ul>
            <div id="unknown">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="sign_up.jsp"><span class="glyphicon glyphicon-user"></span> Sign up</a></li>
                    <li><a href="sign_in.jsp"><span class="glyphicon glyphicon-log-in"></span> Sign in</a></li>
                </ul>
            </div>
            <div id="known" style="display:none">
                <ul class="nav navbar-nav navbar-right">
                    <li id="user_name"><a href="#"><span class="glyphicon glyphicon-user"></span></a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Log out</a></li>
                </ul>
            </div>
        </div>
    </div>
</nav>
<div class="btn-toolbar" role="toolbar">
</div>
<hr/>
<div class="container" style="padding-top: 100px">
    <div class="row">
        <div class="col-sm-5">
            <h2>Built for international students</h2>
            <p class="lead">Roommate finder is a service platform to all the student as well as professional who are looking for the roommate when they go to another city to live or has budget constraint.</p>
        </div>
        <div class="col-sm-5 col-sm-offset-1">
            <form class="form-horizontal" role="form">
            	<p></p>
                <div class="form-group">
                    <label for="firstname" class="col-sm-2 control-label">Username</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="username" placeholder="Please input your username">
                    </div>
                </div>
                <div class="form-group">
                    <label for="lastname" class="col-sm-2 control-label">Password</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="password" placeholder="Input your password">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox">Remember me
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default" id="login">Sign In</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<footer class="footer" style="position: fixed;left: 0;bottom: 0; width: 100%; background-color: beige; color: black; text-align: center;height: 60px;">
    <div class="container">
        <span class="text-muted">this is our footer.</span>
    </div>
</footer>
</body>
</html>
