document.addEventListener("DOMContentLoaded", function() {
	bookingLoginPage.getBookingLoginPage();
});

var bookingLoginPage = {
	getBookingLoginPage: function(){
		var inputTagValidator = new InputTagValidator();
		
		inputTagValidator.validateInputTag(this.elements.bkEmail, inputTagValidator.emailRegex);
		
		this.setEventToUserInfoContainer(inputTagValidator);
		this.setScrollTopEvent();
	},
	
	elements: {
		bkEmail : document.querySelector("#resrv_id"),
		btnLogin : document.querySelector(".login_btn.confirm"),
		btnTop : document.querySelector(".lnk_top")
	},
	
	setEventToUserInfoContainer : function(inputTagValidator){
		this.elements.bkEmail.addEventListener("input", function(event){
			if(inputTagValidator.isValid){
				event.target.parentNode.querySelector(".warning_msg").style.display = "none";
				this.elements.btnLogin.removeAttribute("disabled");
			} else {
				event.target.parentNode.querySelector(".warning_msg").style.display = "inline";
				this.elements.btnLogin.disabled = "true";
			}
		
			if(event.target.value.length === 0){
				event.target.parentNode.querySelector(".warning_msg").style.display = "none";
			}
		}.bind(this));
	},
	
	setScrollTopEvent: function(){
		addScrollTopEvent(this.elements.btnTop);
	}
}