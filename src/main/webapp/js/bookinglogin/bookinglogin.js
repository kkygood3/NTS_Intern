/**
 * bookinglogin 페이지 메인로직 처리
 * @author 시윤
 *
 */

function RegularExpression() {
	this.name = /^([가-힣]{2,})$|^(([A-Z][a-z]*\s)+[A-Z][a-z]*)$/;
	this.tel = /^01[016-9]-[0-9]{3,4}-[0-9]{4}$/;
	this.email = /^[a-z][-\.\w]*@[a-z][-\.\w]*\.[a-z]{2,3}$/i;
}

document.addEventListener("DOMContentLoaded", function() {
	var input = document.querySelector("form input");
	var submitButton = document.querySelector("button.login_btn.confirm");
	submitButton.disabled = true;
	input.addEventListener("change", function(event){
		var value = input.value;
		if (!value) {
			event.target.classList.remove("warning");
			return;
		}
		var regExp = /^[a-z][-\.\w]*@[a-z][-\.\w]*\.[a-z]{2,3}$/i;
		if (regExp.test(value)) {
			event.target.classList.remove("warning");
			submitButton.disabled = false;
		} else {
			event.target.classList.add("warning");
			submitButton.disabled = true;
		}
	});
});
