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
		
		$.ajax({
			type : 'post',
			url : 'LoginControl',
			data :JSON.stringify({
				username : username,
				password : password,
				submit : "login",
			}),
			contentType: "application/json; charset=utf-8",
			dataType : 'json',
			
			success: function(response) {
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

