function loadReservationResponse(response) {
	// 상품 정보
	loadProductDescription(response);

	// 클릭 이벤트
	initClickEvents();
	
	// 입력 이벤트
	initInputChangeEvent();
}




function requestGetAjax(callback, url){
	let ajaxReq = new XMLHttpRequest();
	ajaxReq.callback = callback;
	ajaxReq.addEventListener('load', function(evt) {
		this.callback(evt.target.response);
	});
	
	ajaxReq.open('GET', url);
	ajaxReq.responseType = 'json';
	ajaxReq.send()
}

function requestPostAjax(callback, url, param) {
	let ajaxReq = new XMLHttpRequest();
	ajaxReq.callback = callback;
	ajaxReq.addEventListener('load', function(evt) {
		this.callback(evt.target.response);
	});
	ajaxReq.open('POST', url);
	ajaxReq.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
	ajaxReq.responseType = 'json';
	ajaxReq.send(param)
}

function getUrlParameter(name) {
	let params = location.href.split('?')[1].split('&');
	for (let i = 0; i < params.length; i++) {
		let paramSplited = params[i].split('=');
		let paramName = paramSplited[0];
		let paramValue = paramSplited[1];

		if (paramName === name) {
			return paramValue;
		}
	}
}




function loadProductDescription(response) {
	//
	console.log(response);
	
	let reservationResponse = response;
	let reservationDisplayInfo = response.reservationDisplayInfo;
	let reservationPriceList = response.prices;
	
	let titleContainer = document.querySelector('div.top_title>h2>span.title');
	titleContainer.innerText = reservationDisplayInfo.productDescription;
	
	let imageContainer = document.querySelector('ul.visual_img>li.item>img.img_thumb');
	imageContainer.setAttribute('src',reservationDisplayInfo.productImage);
	
	// 장소 및 시간
	initSchedule(reservationDisplayInfo);
	
	// 최저가, 요금
	initPriceDescription(reservationPriceList);
}

function initClickEvents() {
	initTicketClickEvents();
	initAgreementClickEvents();
	initReserveClickEvents();
}




function initSchedule(reservationDisplayInfo){
	// 장소
	let scheduleContainer = document.querySelector('p.dsc');
	scheduleContainer.childNodes[0] = reservationDisplayInfo.placeStreet;
	
	// 기간
	let randomDate = document.querySelector('#schedule').dataset.id;
	let startDate = new Date(randomDate);
	let endDate = new Date(randomDate);
	endDate.setDate(endDate.getDate()+5);
	
	const DAY_LABEL = ['일','월','화','수','목','금','토'];
	let thumnailDateArea = document.querySelector('.date_area');
	let descDateArea = document.querySelector('.store_details .dsc');
	let placeSchedule = startDate.toLocaleDateString().replace(/ /g,'') + '('+DAY_LABEL[startDate.getDay()]+')~' + 
				endDate.toLocaleDateString().replace(/ /g,'') + '('+DAY_LABEL[endDate.getDay()]+')';

	scheduleContainer.childNodes[2].innerText = placeSchedule;
}
function initPriceDescription(reservationPriceList) {
	// 최저가
	let lowestPrice = reservationPriceList[0].price;
	for(let i = 0; i < reservationPriceList.length; i++) {
		let price = reservationPriceList[i].price;
		let discountRate = reservationPriceList[i].discountRate;
		let discountPrice = parseInt( price * ((100 - discountRate) / 100) );
		if (lowestPrice > discountPrice) {
			lowestPrice = discountPrice;
		}
	}
	let priceContainer = document.querySelector('em.preview_txt_dsc');
	priceContainer.innerText = '₩' + lowestPrice + '~';
	
	// 요금
	let priceDescriptionArea = document.querySelector('.store_details :nth-child(6)');
	
	let priceList = new Array();
	for(let i = 0; i < reservationPriceList.length; i++) {
		priceList[i] = new reservationPriceELement(reservationPriceList[i].price, reservationPriceList[i].priceTypeLabel);
	}
		
	let resultText = '';
	for(let i = 0; i < priceList.length; i++){
		if(i != 0 ){
			resultText += '\r\n';
		}
		resultText += priceList[i].label + ' ' + priceList[i].price + '원';
	}
	
	priceDescriptionArea.innerText = resultText;
}
function reservationPriceELement(price, priceTypeLabel) {
	this.price = price.toString().replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
	this.label = priceTypeLabel;
}




function validateInput(index) {
	const REGULAR_KOREAN_NAME = /^[가-힣]{2,4}$/;
	const REGULAR_TELEPHONE = /^\d{2,3}-\d{3,4}-\d{4}$/;
	const REGULAR_EMAIL = /^[_a-zA-Z0-9-\.]+@[\.a-zA-Z0-9-]+\.[a-zA-Z]+$/;

	let input = document.querySelectorAll('.form_horizontal input')[index];
	let warningArea = document.querySelectorAll('.warning_msg')[index];
	
	let isValid;
	switch (index) {
	case 0:
		let name = input.value;
		isValid = REGULAR_KOREAN_NAME.test(name);
		break;
	case 1:
		let telephone = input.value;
		let isNoHyphenTel = (10 <= telephone.length && telephone.length <=11) && (telephone.indexOf('-') < 0);
		if (isNoHyphenTel) {
			telephone = telephone.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/, "$1-$2-$3");
		}
		isValid = REGULAR_TELEPHONE.test(telephone);
		
		if(isValid){
			input.value = telephone;
		}
		break;
	case 2:
		isValid = REGULAR_EMAIL.test(input.value)
		break;
	default:
		isValid = false;
		break;
	}
	
	if(!isValid){
		warningArea.style.visibility = 'visible';
		setTimeout(function() {
			warningArea.style.visibility = 'hidden';
		}, 1000);
	}
	return isValid;

}




function onReserveClicked() {
	let isValid = validateInput(0);
	isValid = validateInput(1) && isValid;
	isValid = validateInput(2) && isValid;

	if (isValid) {
		postReservation();
	}
}
function postReservation() {
	let ticketInputs = document.querySelectorAll('.section_booking_ticket input');
	let bookerInputs = document.querySelectorAll('.form_horizontal input');
	let bookerName = bookerInputs[0].value;
	let bookerTelephone = bookerInputs[1].value;
	let bookerEmail = bookerInputs[2].value;
	let reservationDate = document.querySelector('#reservationDateInput').value;
	
	let priceInfoList = new Array();
	for (let i = 0; i < ticketInputs.length; i++) {
		let isContainTicket = (ticketInputs[i].value > 0);
		if (isContainTicket) {
			let type = ticketInputs[i].getAttribute('pricetype');
			let count = ticketInputs[i].value;
			priceInfoList.push(new reservationPriceInfo(type, count));
		}
	}
	
	let reserveRequest = JSON.stringify(new setReservationInfo(bookerName,bookerTelephone,bookerEmail,getUrlParameter('displayInfoId'),reservationDate,priceInfoList));
	
	requestPostAjax(postResponseHandler, 'api/reservations', reserveRequest);
}
function postResponseHandler(response){
	if(response === true){
		alert('예약 완료');
		location.href = '/detailProduct?displayInfoId='+getUrlParameter('displayInfoId');
	} else {
		alert('예약에 문제가 발생했습니다.');
		location.reload();
	}
}
function setReservationInfo(name, telephone, email, displayInfoId, reservationDate, priceInfoList){
	this.reservationName = name;
	this.reservationTel = telephone;
	this.reservationEmail = email;
	this.displayInfoId = parseInt(displayInfoId);
	this.reservationDate = reservationDate;
	this.reservationPriceInfoList = priceInfoList;
}

function reservationPriceInfo(type, count){
	this.type = type;
	this.count = count;
}




function initTicketClickEvents() {
	let ticketContainer = document.querySelector('.ticket_body');
	let ticketTotalArea = document.querySelector('#totalCount');

	ticketContainer
			.addEventListener('click',function(evt) {
					evt.preventDefault();
	
					let clickedTag = evt.target;
					if (clickedTag.tagName == 'A') {
						let btnMinus = clickedTag.parentElement.querySelector('.ico_minus3');
						let btnPlus = clickedTag.parentElement.querySelector('.ico_plus3');
	
						let amountArea = clickedTag.parentElement.querySelector('input');
						let amountValue = parseInt(amountArea.getAttribute('value'));
	
						let isMinusClicked = (clickedTag.classList.contains('ico_minus3') && amountValue > 0);
						let isPlusClicked = (clickedTag.classList.contains('ico_plus3'));
	
						let item = clickedTag.parentElement.parentElement.parentElement;
						let cost = item.getAttribute('cost');
						let itemPriceArea = item.querySelector('.total_price');

						if (isMinusClicked) {
							if (amountValue == 1) {
								btnMinus.classList.add('disabled');
								amountArea.classList.add('disabled');
								itemPriceArea.parentElement.classList.remove('on_color');
							}
							
							itemPriceArea.innerText = (cost * (amountValue - 1)).toLocaleString();
							amountArea.setAttribute('value', amountValue - 1);
	
							ticketTotalArea.innerText = parseInt(ticketTotalArea.innerText) - 1;
						} else if (isPlusClicked && checkTicketCountOver()) {
							if (amountValue == 0) {
								btnMinus.classList.remove('disabled');
								amountArea.classList.remove('disabled');
								itemPriceArea.parentElement.classList.add('on_color');
							}
	
							item.querySelector('.total_price').innerText = (cost * (amountValue + 1)).toLocaleString();
							amountArea.setAttribute('value',amountValue + 1);
							
							ticketTotalArea.innerText = parseInt(ticketTotalArea.innerText) + 1;
						}
					}
				});
}
function checkTicketCountOver(){
	const MAX_TICKET_COUNT = 10;

	let ticketInputs = document.querySelectorAll('.ticket_body input');
	let sumOfTickets = 0;
	for(let i = 0 ; i < ticketInputs.length ;i++){
		sumOfTickets += parseInt(ticketInputs[i].value);
	}
	
	if(sumOfTickets >= MAX_TICKET_COUNT){
		alert('한번에 예약 가능한 수('+MAX_TICKET_COUNT+')를 초과할 수 없습니다.');
		return false;
	}
	
	return true;
}
function initAgreementClickEvents() {
	let agreementDescriptionWrap = document.querySelector('.section_booking_agreement');
	
	agreementDescriptionWrap
			.addEventListener('click',
					function(evt) {
						let clickedBtn = evt.target;

						let isClickedChild = (clickedBtn.classList.contains('fn') || clickedBtn.classList.contains('btn_text'));
						if (isClickedChild) {
							clickedBtn = clickedBtn.parentElement;
						}
						
						if (clickedBtn.classList.contains('btn_agreement')) {
							evt.preventDefault();
							let btnTextArea = clickedBtn.querySelector('.btn_text');
							let btnArrow = clickedBtn.querySelector('i.fn');

							let isFold = btnArrow.classList.contains('fn-down2');

							if (isFold) {
								btnTextArea.innerText = '접기';
								btnArrow.classList.add('fn-up2');
								btnArrow.classList.remove('fn-down2');

								clickedBtn.parentElement.classList.add('open');
							} else {
								btnTextArea.innerText = '보기';
								btnArrow.classList.add('fn-down2');
								btnArrow.classList.remove('fn-up2');

								clickedBtn.parentElement.classList.remove('open');
							}
						}
					});
}
function initReserveClickEvents() {
	let reserveBtnWrap = document.querySelector('.bk_btn_wrap');
	let innerBtn = reserveBtnWrap.querySelector('button');
	
	let ticketInputsWrap = document.querySelector('.section_booking_ticket');
	let bookerInputsWrap = document.querySelector('.section_booking_form');

	let ticketInputs = ticketInputsWrap.querySelectorAll('.ticket_body input');
	let bookerInputs = bookerInputsWrap.querySelectorAll('.form_horizontal input');

	let agreementCheckBox = document.querySelector('#chk3');

	ticketInputsWrap.addEventListener('click', checkAvailableReserve);
	bookerInputsWrap.addEventListener('change', checkAvailableReserve);

	function checkAvailableReserve() {
		let isAvailableBtn = false;

		if (agreementCheckBox.checked) {
			// 티켓을 하나라도 선택했는지 확인
			for (let i = 0; i < ticketInputs.length; i++) {
				let targetInput = ticketInputs[i];
				if (targetInput.value > 0) {
					isAvailableBtn = true;
					break;
				} else {
					isAvailableBtn = false;
				}
			}
			// 예약자 정보가 빈칸인지 확인
			for (let i = 0; i < bookerInputs.length; i++) {
				let targetInput = bookerInputs[i];
				if (targetInput.value.length == 0) {
					isAvailableBtn = false;
					break;
				}
			}
		} else {
			isAvailableBtn = false;
		}
		
		if (isAvailableBtn) {
			reserveBtnWrap.classList.remove('disable');
			reserveBtnWrap.addEventListener('click', onReserveClicked);
			innerBtn.style.cursor = '';
		} else {
			reserveBtnWrap.classList.add('disable');
			reserveBtnWrap.removeEventListener('click', onReserveClicked);
			innerBtn.style.cursor = 'not-allowed';
		}

		return isAvailableBtn;
	}
}




function initInputChangeEvent(){
	let bookerInputWrap = document.querySelector('.form_horizontal');
	bookerInputWrap.addEventListener('change',function(evt){
		let changedInput = evt.target;
		if(changedInput.tagName === 'INPUT'){
			validateInput(parseInt(changedInput.getAttribute('idx')));
		}
	});
}




document.addEventListener('DOMContentLoaded', function() {
	requestGetAjax(loadReservationResponse, 'api/reservations?displayInfoId=' + getUrlParameter('displayInfoId'));
});
