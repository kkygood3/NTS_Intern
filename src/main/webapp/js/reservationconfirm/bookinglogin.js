document.addEventListener("DOMContentLoaded", function() {
	var submitButton = document.querySelector("#email_submit");
	
	submitButton.addEventListener("click", function(event) {
		event.preventDefault();
	    var emailInput = document.querySelector("#resrv_id");
	    var emailValidation = (/^[\w+_]\w+@\w+\.\w+(\.\w+)?$/).test(emailInput.value);
	    
	    if(!emailValidation)  {
	    	setWarning("email", emailInput);
	    } else {
    		document.querySelector("#form1").submit();
		}
	});
});


