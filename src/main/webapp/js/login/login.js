/**
 * bookinglogin 페이지 메인로직 처리
 * @author 시윤
 *
 */

document.addEventListener("DOMContentLoaded", function() {
	var input = document.querySelector("form input");
	var submitButton = document.querySelector("button.login_btn.confirm");
	submitButton.disabled = true;
	submitButton.style.cursor = "default";
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
			submitButton.style.cursor = "pointer";
		} else {
			event.target.classList.add("warning");
			submitButton.disabled = true;
			submitButton.style.cursor = "default";
		}
	});
});
