/**
 * 
 */
let reserve = {
		minusButtons : document.querySelectorAll('.ico_minus3'),
		amountValues : document.querySelectorAll('.count_control_input'),
		plusButtons : document.querySelectorAll('.ico_plus3'),
		reserveButton : document.querySelector('.bk_btn'),
		agreementCheckBox : document.querySelector('#chk3'),
		totalCount : document.querySelector('#totalCount'),
		name : document.querySelector('#name'),
		tel : document.querySelector('#tel'),
		email : document.querySelector('#email')
		
}

document.addEventListener('DOMContentLoaded', ()=>{
	addMinusButtonsEvent();
	addPlusButtonsEvent();
	addToggleAgreement();
	// 예약하기 버튼 클릭 시 submit 검사
	reserve.reserveButton.addEventListener('click', e=>validateForm(e));
	
	// 동의 버튼 (check box) check에 이벤트 부여 
	reserve.agreementCheckBox.addEventListener('change', toggleReservationButton);
	
});


function addMinusButtonsEvent(){
	reserve.minusButtons.forEach((element, index)=>{
		
		element.addEventListener('click', e=>{
			let amountElement = e.target.parentElement.children[1];
			let amount = --amountElement.value;
			updateAmountValue(amountElement);
			calcuatePrice(amountElement);
		});
	});
}
function addPlusButtonsEvent(){
	reserve.plusButtons.forEach(element=>{
		element.addEventListener('click', e=>{
			let amountElement = e.target.parentElement.children[1];
			let amount = ++amountElement.value;
			updateAmountValue(amountElement);
			calcuatePrice(amountElement);
		});
	});
}


/**
 * 수량 양 옆의 -, + 버튼 클릭 시 수량을 조절하는 event
 * @param amountElement input type의 amount
 * @returns
 */
function updateAmountValue(amountElement){
	let amount = amountElement.value;
	if(amount < 1){
		amountElement.value = 0;
		if(amount < 0){
			return;
		}
		amountElement.classList.add('disabled');
		amountElement.parentElement.children[0].classList.add('disabled');
		amountElement.parentElement.parentElement.children[1].classList.remove('on_color');
	}else{
		amountElement.classList.remove('disabled');
		amountElement.parentElement.children[0].classList.remove('disabled');
		amountElement.parentElement.parentElement.children[1].classList.add('on_color');
	}
	
	updateTotalCount();
}

/**
 * (예매자 정보)총 매수를 계산
 */
function updateTotalCount(){
	let totalCount = 0;
	reserve.amountValues.forEach(element=>{
		totalCount += parseInt(element.value);
	});
	reserve.totalCount.innerText = totalCount;
}

/**
 * 가격과 수량에 따라 price를 계산
 * @param amountElement 수량을 나타내는 input type의 element
 */
function calcuatePrice(amountElement){
	let price = parseInt(amountElement.dataset.price);
	let amount = parseInt(amountElement.value);
	event.target.closest('div.count_control').children[1].firstElementChild.innerText = new Intl.NumberFormat('en-IN').format(price * amount);
}




/**
 * Agreement에서 보기 click시 발생하는 event
 */
function addToggleAgreement(){
	let agreementButton = document.querySelectorAll('.btn_agreement');
	agreementButton.forEach(button=>{
		button.addEventListener('click', e=>{
			let agreementBox = e.target.closest('div.agreement');
			if(agreementBox.classList.contains('open')){
				agreementBox.classList.remove('open');
			}else{
				agreementBox.classList.add('open');
			}
		});
	});
}

/**
 * Form의 각 value가 정규표현식에 맞는 지 확인
 */
function validateForm(event){
	if(!reserve.agreementCheckBox.checked){
		alert('이용자 약관을 동의해주세요.');
		reserve.agreementCheckBox.focus();
		return;
	}
	
	let nameReg = /[a-zA-Z가-힣]{2,}/;
	if(!nameReg.test(reserve.name.value)){
		alert('이름 형식이 올바르지 않습니다.');
		reserve.name.focus();
		return;
	}
	
	let telReg = /^01[016789]\d{7,8}$/;
	if(!telReg.test(reserve.tel.value)){
		alert('휴대폰 번호 형식이 올바르지 않습니다.');
		reserve.tel.focus();
		return;
	}
	
	let emailReg = /\w{4,}@\w+\.\w+/;
	if(!emailReg.test(reserve.email.value)){
		alert('이메일 형식이 올바르지 않습니다.');
		reserve.email.focus();
		return;
	}
	
	if(totalCount.innerText == 0){
		// TODO TEST!!
		alert('예매 수량이 없습니다.');
		document.querySelector('.ticket_body').focus();
		return;
	}
	
	reserveSubmit();
}

/**
 * ReservationPrice constructor
 * @param productPriceId
 * @param count
 * @returns
 */
function ReservationPrice(productPriceId, count){
	this.productPriceId = productPriceId;
	this.count = count;
}

/**
 * 가격 정보, 회원 정보 등을 json data로 합치는 function 
 * @returns jsonData reservation의 정보들을 담은 json data
 */
function combineReservationDataForJson(){
	let reservationParam = {};
	let reservationPrices = Array.from(reserve.amountValues)
								.filter(price=>{return price.value>0})
								.map(function(price){
		return new ReservationPrice(price.dataset.priceid, price.value);
	});

	reservationParam.displayInfoId = document.querySelector('.bk_btn').dataset.id;
	reservationParam.productId = document.querySelector('.bk_btn').dataset.productId;
	reservationParam.price = reservationPrices;
	reservationParam.reservationEmail = reserve.email.value;
	reservationParam.reservationName = reserve.name.value;
	reservationParam.reservationTel = reserve.tel.value;

	return JSON.stringify(reservationParam);
}

function reserveSubmit() {
	
	let oReq = new XMLHttpRequest();
	oReq.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			alert('success');
		}
	};
	oReq.open('POST', '/api/reservations');
	oReq.setRequestHeader('Content-type', 'application/json;charset=utf-8');
	oReq.send(combineReservationDataForJson());
}

/**
 * 동의 버튼의 상태에 따라 '예약하기' 버튼 활성화/비활성화
 */
function toggleReservationButton(){
	let reservationButton = document.querySelector('.bk_btn_wrap');
	if(reserve.agreementCheckBox.checked){
		reservationButton.classList.remove('disable');
	}else{
		reservationButton.classList.add('disable');
	}
}

