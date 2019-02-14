document.addEventListener("DOMContentLoaded", function() {
	getBookingLoginPage();
});

function getBookingLoginPage(){
	this.setEvent = new SetEvent();
	this.emailIsValid = false;
	
	this.bkEmail = document.querySelector("#resrv_id")
	setEvent.validateInputValue(bkEmail, /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.(com|net|co\.kr)$/);
	
	setEvent.scrollTop();
	setEvent.linkToMainPage();
	setEvent.login();
}

function SetEvent(){}

SetEvent.prototype.validateInputValue = function(inputTag, regularExpression){
	inputTag.addEventListener("input", function(event){
		if(event.target.value.length === 0 || regularExpression.test(event.target.value)){
			event.target.parentNode.querySelector(".warning_msg").style.display = "none";
			emailIsValid = true;
		} else {
			event.target.parentNode.querySelector(".warning_msg").style.display = "inline";
			emailIsValid = false
		}
	});
}

SetEvent.prototype.scrollTop = function(){
	document.querySelector(".lnk_top").addEventListener("click", function(){
		event.preventDefault();
		document.documentElement.scrollTop = 0;
	});
}

SetEvent.prototype.linkToMainPage = function(){
	document.querySelector(".nbooking_logo.spr_bi.txt_logo").addEventListener("click", function(){
		event.preventDefault();
		window.location.href = "/reservation";
	});
}

SetEvent.prototype.login = function(){
	document.querySelector(".login_btn.confirm").addEventListener("click", function(){
		event.preventDefault();
		if(emailIsValid){
			var httpRequest;
			
			if (window.XMLHttpRequest) {
				httpRequest =  new XMLHttpRequest();
				
				httpRequest.onreadystatechange = function() {
					var jsonResponse;
					
					if (httpRequest.readyState === 4 && httpRequest.status === 200) {
						window.location.href = "history";
					}
				}
				
				httpRequest.open("POST", "loginConfirm");
				httpRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				httpRequest.send("userEmail=" + bkEmail.value);
			}
		}
	});
}