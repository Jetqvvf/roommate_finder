$(document).ready(function() {
	
	function setErrorMsg(id, errormsg) {
		$("p").eq(id).css("color", "red").text(errormsg);
	}
	
	$('#profile-save').on("click", function() { 
		var phone_number = $('#phone-number').val();
		var city = $('#city').val();
		var state = $('#state').val();
		var zip_code = $('#zip-code').val();
		var language = $('#language').val();
		var roommate_number = $('#roommate-number').val();
		var department = $('#department').val();
		var track = $('#track').val();
		var cooking = $('#cooking').val();
		var non_veg = $('#non-veg').val();
		var smoking = $('#smoking').val();
		var drinking = $('#drinking').val();
		var pets = $('#pets').val();
		
		
		
		if ("" == username || "" == password) {
			setErrorMsg(0, "The username or password is empty");
		}
		
		$.ajax({
			type : 'post',
			url : 'ProfileEdit',
			data :JSON.stringify({
				phone_number : phone_number,
				address : address,
				city : city,
				state : state,
				zip_code : zip_code,
				language : language,
				roommate_number : roommate_number,
				department : department, 
				track : track,
				cooking : cooking,
				non_veg : non_veg,
				smoking : smoking,
				drinking : drinking,
				pets : pets
			}),
			contentType: "application/json; charset=utf-8",
			dataType : 'json',
			
			success: function(response) {
				if (response.error == 0) {
					alert(response.msg);
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

