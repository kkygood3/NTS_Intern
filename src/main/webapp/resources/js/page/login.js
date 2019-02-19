document.addEventListener("DOMContentLoaded", () => {
	addEventClickLogin();
});

function addEventClickLogin(){
	var btnSubmit = document.querySelector("#btn_submit");
	var form = document.querySelector("#login_form");
	
	btnSubmit.addEventListener("click", (evt) =>{
		evt.preventDefault();
		var inputEmail = document.querySelector("#email");
		var email = inputEmail.value;
		const emailRegex = /[.\-\w]+@\w+[.]?[\-\w]+[.]?[\w]+/;
		
		if(email === "" || email.match(emailRegex) === null
				|| email.match(emailRegex)[0] !== email){
			alert("잘못된 Email 형식 입니다.");
		}
		else{
			form.submit();
		}
	});
}