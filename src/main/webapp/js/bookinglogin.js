document.addEventListener("DOMContentLoaded", function() {
	bookingLoginPage.getBookingLoginPage();
});

var bookingLoginPage = {
	getBookingLoginPage: function(){
		this.validateInputValue(this.elements.bkEmail, this.emailRegex);
		this.setScrollTopEvent();
	},
	
	elements: {
		bkEmail : document.querySelector("#resrv_id"),
		btnLogin : document.querySelector(".login_btn.confirm"),
		btnTop : document.querySelector(".lnk_top")
	},
	
	emailRegex : /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.(com|net|co\.kr)$/,
	
	validateInputValue : function(inputTag, regularExpression){
		inputTag.addEventListener("input", function(event){
			if(regularExpression.test(event.target.value)){
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