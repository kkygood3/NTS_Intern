/**
 * bookinglogin 페이지 메인로직 처리
 * @author 시윤
 *
 */

document.addEventListener("DOMContentLoaded", function() {
	addInputChangeEventListener();
	addSubmitButtonChangeEventListener();
});

function addSubmitButtonChangeEventListener() {
	var submitButton = document.querySelector("button.login_btn.confirm");
	var input = document.querySelector("form input");
	var form = document.getElementById("form1");
	submitButton.addEventListener("click", function() {
		var email = input.value;
		if (isValidEmail(email)) {
			form.submit();
		} else {
			var invalidEmail = document.querySelector("p.invalid_email");
			invalidEmail.style.display = "block";
		}
	});
}

function addInputChangeEventListener() {
	var input = document.querySelector("form input");
	var submitButton = document.querySelector("button.login_btn.confirm");
	submitButton.style.cursor = "default";
	input.addEventListener("change", function(event){
		var email = input.value;
		if (email == "") {
			event.target.classList.remove("warning");
			return;
		}
		if (isValidEmail(email)) {
			event.target.classList.remove("warning");
		} else {
			event.target.classList.add("warning");
		}
	});
}

function isValidEmail(email) {
	var regExp = /^[a-z][-\.\w]*@[a-z][-\.\w]*\.[a-z]{2,3}$/i;
	if (regExp.test(email)) {
		return true;
	} else {
		return false;
	}
}
