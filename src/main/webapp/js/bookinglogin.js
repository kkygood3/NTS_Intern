document.addEventListener("DOMContentLoaded", function() {
	bookingLoginPage.getBookingLoginPage();
});

var bookingLoginPage = {
	getBookingLoginPage: function(){
		var inputTagValidator = new InputValueValidator();
		inputTagValidator.validateInputText(this.elements.bkEmail, inputTagValidator.emailRegex);
		
		this.allowSubmitIfValidInput(inputTagValidator);

		addScrollTopEvent(this.elements.btnTop);
	},
	
	elements: {
		bkEmail : document.querySelector("#resrv_id"),
		btnLogin : document.querySelector(".login_btn.confirm"),
		msgWarn : document.querySelector(".warning_msg"),
		btnTop : document.querySelector(".lnk_top")
	},
	
	allowSubmitIfValidInput : function(inputTagValidator){
		this.elements.bkEmail.addEventListener("input", function(event){
			if(inputTagValidator.isValid){
				this.elements.msgWarn.style.display = "none";
				this.elements.btnLogin.removeAttribute("disabled");
			} else {
				this.elements.msgWarn.style.display = "";
				this.elements.btnLogin.disabled = "true";
			}
		
			if(event.target.value.length === 0){
				this.elements.msgWarn.style.display = "none";
			}
		}.bind(this));
	}
}