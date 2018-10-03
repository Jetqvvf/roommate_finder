$(document).ready(function() {
	$.ajax({
		type : 'post',
		url : 'ProfileControl',
		data :JSON.stringify({
			type : "getprofile"
		}),
		contentType: "application/json; charset=utf-8",
		dataType : 'json',
		
		success: function(response) {
			if (response.error == 0) {
				$('#phone-number').val(response.phone_number);
				$('#city').val(response.city);
				$('#state').val(response.state);
				$('#zip-code').val(response.zip_code);
				$('#language').val(response.language);
				$('#roommate-number').val(response.number_of_mates);
				$('#department').val(response.department);
				$('#track').val(response.track);
				$('#address').val(response.address);
				
				if (0 == response.cooking) {
					$('#cooking').prop('checked', false);
				} else {
					$('#cooking').prop('checked', true);
				}
				
				if (0 == response.non_veg) {
					$('#non-veg').prop('checked', false);
				} else {
					$('#non-veg').prop('checked', true);
				}
				
				if (0 == response.smoking) {
					$('#smoking').prop('checked', false);
				} else {
					$('#smoking').prop('checked', true);
				}
				
				if (0 == response.drinking) {
					$('#drinking').prop('checked', false);
				} else {
					$('#drinking').prop('checked', true);
				}
				if (0 == response.pets) {
					$('#pets').prop('checked', false);
				} else {
					$('#pets').prop('checked', true);
				}
				if (0 == response.cooking) {
					$('#cooking').prop('checked', false);
				} else {
					$('#cooking').prop('checked', true);
				}
				
			} else {
				
			} 
		},
		error: function() {
		}
	});
	$('#logout').on("click", function() {
		$.ajax({
			type : 'post',
			url : 'LoginControl',
			data :JSON.stringify({
				submit : "logout"
			}),
			contentType: "application/json; charset=utf-8",
			dataType : 'json',
			
			success: function(response) {
				if (response.error == 0) {
					location.href = "homepage.jsp";
				} else {
					alert("modify error");
				} 
			},
			error: function() {
			}
		});
	});
	$('#profile-save').on("click", function() { 
		var phone_number = $('#phone-number').val();
		var city = $('#city').val();
		var state = $('#state').val();
		var zip_code = $('#zip-code').val();
		var language = $('#language').val();
		var roommate_number = $('#roommate-number').val();
		var department = $('#department').val();
		var track = $('#track').val();
		var cooking = +$('#cooking').is(':checked' );
		var non_veg = +$('#non-veg').is( ':checked' );
		var smoking = +$('#smoking').is( ':checked' );
		var drinking = +$('#drinking').is( ':checked' );
		var pets = +$('#pets').is( ':checked' );
		var address = $('#address').val();
		
		
		$.ajax({
			type : 'post',
			url : 'ProfileControl',
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
				pets : pets,
				address : address,
				type : "modify"
			}),
			contentType: "application/json; charset=utf-8",
			dataType : 'json',
			
			success: function(response) {
				if (response.error == 0) {
					alert(response.msg);
					//modified by Qiao Wang @20180920
				} else {
					alert("modify error");
				} 
			},
			error: function() {
			}
		});
	})
});

