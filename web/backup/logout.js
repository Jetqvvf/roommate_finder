$(document).ready(function() {
	
	$('#logout').on("click", function() { 	
		$.ajax({
			type : 'post',
			url : 'LoginControl',
			data :JSON.stringify({
				submit : "logout",
			}),
			contentType: "application/json; charset=utf-8",
			dataType : 'json',
			
			success: function(response) {
				if (response.error == 0) {
					location.href = "sign_in.html";
				} else {
					setErrorMsg(0, "username or password is invalid");
				} 
			},
			error: function() {
			}
		});
	})
});

