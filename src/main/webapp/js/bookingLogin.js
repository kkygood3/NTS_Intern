function initFormBtn(){
	const REG_EMAIL = /^[_a-zA-Z0-9-\.]+@[\.a-zA-Z0-9-]+\.[a-zA-Z]+$/;
	
	let bookForm = document.querySelector('#form1');
	
	let emailInput = document.querySelector('#form1').querySelector('.login_input');
	let submitBtn = document.querySelector('#form1').querySelector('.login_btn');
	
	submitBtn.addEventListener('click',function(evt){
		evt.preventDefault();
		
		let isVaildEmail = REG_EMAIL.test(emailInput.value);
		if(isVaildEmail){
			bookForm.submit();
		} else {
			alert('이메일 형식이 아닙니다.');
		}
	})
}

document.addEventListener('DOMContentLoaded', function() {
	initFormBtn();
});