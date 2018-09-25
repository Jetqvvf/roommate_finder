$(document).ready(function() {
	$("input").after("<span></span>");
	$("span").hide();
	
	function isRightName(username) {
		var pattern = /^[\.a-zA-Z]+$/;
		return pattern.test(username);
	}
	
	function isRightUserName(username) {
		var pattern = /^[\.\da-zA-Z]+$/;
		return pattern.test(username);
	}

	function isRightPassword(password) {
		var pattern = /^.{6,}$/;
		return pattern.test(password);
	}
	
	function isRightRepassword(password) {
		return (password==$('#password').val())
	}
	
	
	function isRightEmail(email) {
		var pattern = /^[\da-zA-Z]+@[/da-zA-Z]+\.[a-zA-Z]{3}$/;
		return pattern.test(email);
	}	
	
	function setErrorMsg(elementid, id, errormsg) {
		$("span").eq(id).removeClass("glyphicon glyphicon-ok form-control-feedback");
		$("span").eq(id).addClass("glyphicon glyphicon-remove form-control-feedback");
		$("span").eq(id).show();
		$(elementid).parent().parent().removeClass("has-success");
		$(elementid).parent().parent().addClass("has-error");
		$("p").eq(id + 1).css("color", "red").text(errormsg);
	}
	
	function checkElment(elementid, id, isRight, msg, msg1) {
		var value = $(elementid).val();
		if (value == "") {
			$("span").eq(id).hide();
			$(elementid).parent().parent().removeClass("has-success");
			$(elementid).parent().parent().removeClass("has-error");
		} else {
			if (!isRight(value)) {
				$("span").eq(id).removeClass("glyphicon glyphicon-ok form-control-feedback");
				$("span").eq(id).addClass("glyphicon glyphicon-remove form-control-feedback");
				$("span").eq(id).show();
				$(elementid).parent().parent().removeClass("has-success");
				$(elementid).parent().parent().addClass("has-error");
				$("p").eq(id + 1).css("color", "red").text(msg);
			} else {
				$("span").eq(id).removeClass("glyphicon glyphicon-remove form-control-feedback");
				$("span").eq(id).addClass("glyphicon glyphicon-ok form-control-feedback");
				$("span").eq(id).show();
				$(elementid).parent().parent().removeClass("has-error");
				$(elementid).parent().parent().addClass( "has-success" );
				$("p").eq(id + 1).css("color", "black").text(msg1);
			}
		}
	} 
	
	$('#first-name').keyup(function(){
		checkElment("#first-name", 0, isRightName, "First name is invalid", "Use letters.");
	});
	
	$('#last-name').keyup(function(){
		checkElment("#last-name", 1, isRightName, "Last name is invalid", "Use letters.");
	});
	
	$('#username').keyup(function(){
		checkElment("#username", 2, isRightUserName, "Username is invalid", "Use letters and numbers");
	});
	
	
	$('#email').keyup(function(){
		checkElment("#email", 3, isRightEmail, "Email is invalid", "We’ll never share your email address with anyone.");
	});
	
	$('#password').keyup(function(){
		checkElment("#password", 4, isRightPassword, "Password is invalid", "Use at least one lowercase letter, one numeral, and seven characters.");
	});
	
	$('#re-password').keyup(function(){
		checkElment("#re-password", 5, isRightRepassword, "The two passwords are not the same", "Please confirm the password.");
	});
	
	
	$('#register').on("click", function() { 
		var username = $('#username').val();
		var password = $('#password').val();
		var first_name = $('#first-name').val();
		var last_name = $('#last-name').val();
		var email = $('#email').val();
		var re_password = $('#re-password').val();
		
		if (!isRightName(first_name)) {
			$('#first-name').focus();
			setErrorMsg("#first-name", 0, "First name is invalid");
			return false;
		}
		
		if (!isRightName(last_name)) {
			$('#last-name').focus();
			setErrorMsg("#last-name", 1, "Last name is invalid");
			return false;
		}
		
		if (!isRightUserName(username)) {
			$('#username').focus();
			setErrorMsg("#username", 2, "Username is invalid");
			return false;
		}

		if (!isRightEmail(email)) {
			$('#email').focus();
			setErrorMsg("#email", 3, "Email is invalid");
			return false;
		}
		
		if (!isRightPassword(password)) {
			$('#password').focus();
			setErrorMsg("#password", 4, "Password is invalid");
			return false;
		}
		
		if (!isRightRepassword(re_password)) {
			$('#re-password').focus();
			setErrorMsg("#re-password", 4, "The two passwords are not the same");
			return false;
		}
		
		$.ajax({
			type : 'post',
			url : 'UserController',
			data :JSON.stringify({
				username : username,
				password : password,
				re_password : re_password,
				first_name : first_name,
				last_name : last_name,
				email : email,
				re_password : re_password,
				submit : "register"
			}),
			contentType: "application/json; charset=utf-8",
			dataType : 'json',
			
			success: function(response) {
				if (response.error == 0) {
                    window.location.href='main/homepage.html';
				} else if (1 == response.error) {
					$('#username').focus();
					setErrorMsg("#username", 2, response.msg);
				} else if (2 == response.error) {
					$('#email').focus();
					setErrorMsg("#email", 3, response.msg);
				}
			},
			error: function() {
			}
		});
	})
});

