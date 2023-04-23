function loginRegisterSwitch() {
	$('form').animate({
		height : "toggle",
		opacity : "toggle"
	}, "slow");
}

function showAlertAfterRegistration() {
	$('div.alert.alert-success').show();
}

$('.message a').click(function() {
	loginRegisterSwitch();
});

$("button.register")
		.click(
				function() {
					let name = $("form.register-form input.name")
							.val();
					let lastName = $("form.register-form input.lastName").val();
					let email = $("form.register-form input.email").val();
					let password = $("form.register-form input.password").val();
					let cpassword = $("form.register-form input.cpassword")
							.val();

					if (name == '' || lastName == '' || email == ''
							|| password == '' || cpassword == '') {
						alert("Please fill all fields.");
					} else if ((password.length) < 4) {
						alert("Password should contain at least 4 characters.");
					} else if (!(password).match(cpassword)) {
						alert("Incorrect passwords.");
					} else {
						var userRegistration = {
							name : name,
							lastName : lastName,
							email : email,
							password : password
						};

						$.post("registration", userRegistration,
								function(data) {
									if (data == 'Success') {
										$("form")[0].reset();
										$("form")[1].reset();
										loginRegisterSwitch();
										showAlertAfterRegistration();
									}
								});
					}
				});

$("button.login").click(function() {
	var email = $("form.login-form input.email").val();
	var password = $("form.login-form input.password").val();

	if (email == '' || password == '') {
		alert("Please fill login form.");
	} else {
		var userLogin = {
			email : email,
			password : password
		};

		$.post("login", userLogin, function(data) {
			if(data !== ''){
				var customUrl = '';
				var urlContent = window.location.href.split('/');
				for (var i = 0; i < urlContent.length-1; i++) {
					customUrl+=urlContent[i]+'/'
				}
				customUrl+=data.destinationUrl;
				window.location = customUrl;
			}
			$("form")[1].reset();
		});
	}
});