/**
 * 
 */
const reserve = {
	minusButtons : document.querySelectorAll('.ico_minus3'),
	amountValues : document.querySelectorAll('.count_control_input'),
	plusButtons : document.querySelectorAll('.ico_plus3'),
	reserveButton : document.querySelector('.bk_btn'),
	agreementCheckBox : document.querySelector('#chk3'),
	totalCount : document.querySelector('#totalCount'),
	name : document.querySelector('#name'),
	tel : document.querySelector('#tel'),
	email : document.querySelector('#email'),
	chargeBox : document.querySelector('#charge'),
	countBoxes : document.querySelectorAll('.qty'),
	type : new Map([['A', '성인'], ['Y', '청소년'], ['B', '유아'], ['S', '셋트'], ['D', '장애인'], ['C', '지역주민'], ['E', '얼리버드'], ['V', 'VIP'], ['R', 'R석']])
}

document.addEventListener('DOMContentLoaded', ()=>{
	
	new ConvertPriceType(reserve.chargeBox, reserve.countBoxes);
	new AmountControlButton();
	new ReserveButton();
	
});


/*********************************************/
/************ Convert Price Type *************/
/*********************************************/
function ConvertPriceType(chargeBox, countBoxes){
	this.chargeBox = chargeBox;
	this.countBoxes = countBoxes;
	this.init();
}
ConvertPriceType.prototype = {
	init : function(){
		Array.from(this.countBoxes).forEach(box=>{
			let originType = box.children[1].firstElementChild.firstElementChild.innerText; 
			let changedType = reserve.type.get(originType);
			let price = box.children[1].children[1].firstElementChild.innerText;

			box.children[1].firstElementChild.firstElementChild.innerText = changedType;
			this.chargeBox.innerHTML += changedType + ' : ' + price + '원<br>';
		});
	}
}

/*********************************************/
/*********** Amount Control Button ***********/
/*********************************************/
function AmountControlButton(){
	this.init();
}

AmountControlButton.prototype = {
	init : function(){
		this.addMinusButtonsEvent();
		this.addPlusButtonsEvent();
	},
	addMinusButtonsEvent : function(){
		reserve.minusButtons.forEach(element=>{
			element.addEventListener('click', e=>{
				let amountElement = e.target.parentElement.children[1];
				let amount = --amountElement.value;
				this.updateAmountValue(amountElement);
				this.calcuatePrice(amountElement);
			});
		});
	},
	addPlusButtonsEvent : function(){
		reserve.plusButtons.forEach(element=>{
			element.addEventListener('click', e=>{
				let amountElement = e.target.parentElement.children[1];
				let amount = ++amountElement.value;
				this.updateAmountValue(amountElement);
				this.calcuatePrice(amountElement);
			});
		});
	},
	/**
	 * 수량 양 옆의 -, + 버튼 클릭 시 수량을 조절하는 event
	 * @param amountElement input type의 amount
	 */
	updateAmountValue : function(amountElement){
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
		
		this.updateTotalCount();
	},
	/**
	 * (예매자 정보)총 매수를 계산
	 */
	updateTotalCount : function(){
		let totalCount = 0;
		reserve.amountValues.forEach(element=>{
			totalCount += parseInt(element.value);
		});
		reserve.totalCount.innerText = totalCount;
	},
	/**
	 * 가격과 수량에 따라 price를 계산
	 * @param amountElement 수량을 나타내는 input type의 element
	 */
	calcuatePrice : function(amountElement){
		let price = parseInt(amountElement.dataset.price);
		let amount = parseInt(amountElement.value);
		event.target.closest('div.count_control').children[1].firstElementChild.innerText = new Intl.NumberFormat('ko').format(price * amount);
	}
}



/*********************************************/
/************ Reservation Button *************/
/*********************************************/
function ReserveButton(){
	this.init();
}
function ReservationPrice(productPriceId, count){
	this.productPriceId = productPriceId;
	this.count = count;
}
ReserveButton.prototype = {
	init : function(){
		reserve.reserveButton.addEventListener('click', event => this.validateForm(event));
		reserve.agreementCheckBox.addEventListener('change', this.toggleReservationButton);
		this.addToggleAgreement();
	},
	/**
	 * 동의 버튼의 상태에 따라 '예약하기' 버튼 활성화/비활성화
	 */
	toggleReservationButton : function(){
		let reservationButton = document.querySelector('.bk_btn_wrap');
		if(reserve.agreementCheckBox.checked){
			reservationButton.classList.remove('disable');
		}else{
			reservationButton.classList.add('disable');
		}
	},
	/**
	 * Form의 각 value가 정규표현식에 맞는 지 확인
	 */
	validateForm : function(event){
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
		
		this.submitReservation();
	},
	submitReservation : function() {
		
		let oReq = new XMLHttpRequest();
		oReq.onreadystatechange = function(data) {
			if (this.readyState == 4 && this.status == 200) {
				let reservationReponse = data.target.response;
				reservationReponse.reservationDate = reservationReponse.reservationDate.substring(0, 10)
				let message = `성함 : ${reservationReponse.reservationName}
이메일 : ${reservationReponse.reservationEmail}
연락처 : ${reservationReponse.reservationTel}
공연 날짜 : ${reservationReponse.reservationDate}

로 예매가 완료되었습니다.`;
				alert(message);
				location.href = '/';
			}
		};
		oReq.open('POST', '/api/reservations');
		oReq.setRequestHeader('Content-type', 'application/json;charset=utf-8');
		oReq.responseType = 'json';
		oReq.send(this.combineReservationDataForJson());
	},
	/**
	 * 가격 정보, 회원 정보 등을 json data로 합치는 function 
	 * @returns jsonData reservation의 정보들을 담은 json data
	 */
	combineReservationDataForJson : function(){
		let reservationParam = {};
		let reservationPrices = Array.from(reserve.amountValues)
									.filter(price=>{return price.value>0})
									.map(price=>{
			return new ReservationPrice(price.dataset.priceid, price.value);
		});

		reservationParam.displayInfoId = document.querySelector('.bk_btn').dataset.id;
		reservationParam.productId = document.querySelector('.bk_btn').dataset.productId;
		reservationParam.price = reservationPrices;
		reservationParam.reservationEmail = reserve.email.value;
		reservationParam.reservationName = reserve.name.value;
		reservationParam.reservationTel = reserve.tel.value;
		reservationParam.reservationDate = document.querySelector('#reservation_date').value;
		return JSON.stringify(reservationParam);
	},
	/**
	 * Agreement에서 보기 click시 발생하는 event
	 */
	addToggleAgreement : function(){
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
}

