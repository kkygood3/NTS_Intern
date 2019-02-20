/**
 * 서버에서 전달받은 가격 객체를 javascript에서 관리하기위해 사용
 * @param price
 * @param priceTypeName
 */
function ReservePrice(price, priceTypeLabel) {
	this.price = price.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
	this.label = priceTypeLabel;
}

/**
 * form으로 가격정보를 서버에 전달하기위해 사용
 * 
 * @param type
 * @param count
 */
function PriceInfo(type, count) {
	this.type = type;
	this.count = count;
};

function getKoreanDay(day){
	return DAY_LABEL[day];
}

// 예약 티켓 수량 증감 이벤트 등록
function initTicketClickEvents() {
	var ticketContainer = document.querySelector('.ticket_body');
	var ticketTotalArea = document.querySelector('#totalCount');

	ticketContainer
			.addEventListener('click',function(evt) {
					evt.preventDefault();
	
					var clickedTag = evt.target;
					if (clickedTag.tagName == 'A') {
						var btnMinus = clickedTag.parentElement.querySelector('.ico_minus3');
						var btnPlus = clickedTag.parentElement.querySelector('.ico_plus3');
	
						var amountArea = clickedTag.parentElement.querySelector('input');
						var amountValue = parseInt(amountArea.getAttribute('value'));
	
						var isMinusClicked = (clickedTag.classList.contains('ico_minus3') && amountValue > 0);
						var isPlusClicked = (clickedTag.classList.contains('ico_plus3'));
	
						var item = clickedTag.parentElement.parentElement.parentElement;
						var cost = item.getAttribute('cost');
						var itemPriceArea = item.querySelector('.total_price');

						/**
						 * 감소 버튼을 눌렀을 때 수량이 0이면 -버튼, 수량, 총액의 CSS class를 조정하여
						 * 초록색으로 표시. 증가 버튼을 눌렀을 때 수량이 1이면 -버튼, 수량, 총액의 CSS
						 * class를 조정하여 회색으로 표시.
						 */
						if (isMinusClicked) {
							if (amountValue == 1) {
								btnMinus.classList.add('disabled');
								amountArea.classList.add('disabled');
								itemPriceArea.parentElement.classList.remove('on_color');
							}
							
							itemPriceArea.innerText = (cost * (amountValue - 1)).toLocaleString();
							amountArea.setAttribute('value', amountValue - 1);
	
							ticketTotalArea.innerText = parseInt(ticketTotalArea.innerText) - 1;
						} else if (isPlusClicked) {
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

// 약관 보기 버튼 클릭이벤트 등록
function initAgreementClickEvents() {
	var agreementDescriptionWrap = document.querySelector('.section_booking_agreement');
	
	agreementDescriptionWrap
			.addEventListener('click',
					function(evt) {
						var clickedBtn = evt.target;

						var isClickedChild = (clickedBtn.classList.contains('fn') || clickedBtn.classList.contains('btn_text'));
						if (isClickedChild) {
							clickedBtn = clickedBtn.parentElement;
						}
						
						if (clickedBtn.classList.contains('btn_agreement')) {
							evt.preventDefault();
							var btnTextArea = clickedBtn.querySelector('.btn_text');
							var btnArrow = clickedBtn.querySelector('i.fn');

							var isFold = btnArrow.classList.contains('fn-down2');

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

/**
 * 예매하기 버튼을 클릭할 수 있는지 체크 유효성 검증은 예약 버튼 클릭이벤트에서 처리하므로 활성/비활성화만 검증한다.
 */
function initReserveClickEvents() {
	var reserveBtnWrap = document.querySelector('.bk_btn_wrap');
	var innerBtn = reserveBtnWrap.querySelector('button');
	
	var ticketInputsWrap = document.querySelector('.section_booking_ticket');
	var bookerInputsWrap = document.querySelector('.section_booking_form');

	var ticketInputs = ticketInputsWrap.querySelectorAll('.ticket_body input');
	var bookerInputs = bookerInputsWrap.querySelectorAll('.form_horizontal input');

	var agreementCheckBox = document.querySelector('#chk3');

	ticketInputsWrap.addEventListener('click', checkAvailableReserve);
	bookerInputsWrap.addEventListener('change', checkAvailableReserve);

	/**
	 * 티켓 click, input change 이벤트 처리 함수. 이름, 전화번호, 이메일, 티켓 수량, 약관 동의여부를 확인하고 빈
	 * 칸이 없을때 예약 버튼에 클릭 이벤트를 등록
	 */
	function checkAvailableReserve() {
		var isAvailableBtn = true;

		if (agreementCheckBox.checked) {
			// 티켓을 하나라도 선택했는지 확인
			for (var i = 0; i < ticketInputs.length; i++) {
				var targetInput = ticketInputs[i];
				if (targetInput.value > 0) {
					isAvailableBtn = true;
					break;
				} else {
					isAvailableBtn = false;
				}
			}

			// 예약자 정보가 빈 칸인지 확인
			for (var i = 0; i < bookerInputs.length; i++) {
				var targetInput = bookerInputs[i];
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

function initBackClickEvents() {
	document.querySelector('.btn_back').setAttribute('href','detail?id=' + getUrlParameter('id'));
}

/**
 * 예매자 정보를 검증하고 옳지 않다면 경고 메세지를 출력
 * @param index - 예매자 정보의 input index 
 */
function validateInput(index) {
	var input = document.querySelectorAll('.form_horizontal input')[index];
	var warningArea = document.querySelectorAll('.warning_msg')[index];
	
	var isValid;
	switch (index) {
	case 0:
		// Name
		isValid = input.value.length > 0;
		break;
	case 1:
		/**
		 * 하이픈(-)
		 * 0개일 때 -> 하이픈 없는 문자.
		 * 2개일 때 -> 처리없이 테스트
		 * 나머지 -> 오류
		 */
		var telephone = input.value;
		var isNoHyphenTel = (10 <= telephone.length && telephone.length <=11) && (telephone.indexOf('-') < 0);
		if (isNoHyphenTel) {
			telephone = telephone.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/, "$1-$2-$3");
		}
		isValid = REG_TELEPHONE.test(telephone);
		
		if(isValid){
			input.value = telephone;
		}
		break;
	case 2:
		// Email
		isValid = REG_EMAIL.test(input.value)
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

/**
 * 예약하기 버튼을 클릭했을때 이벤트 처리. 예약자 정보들을 검증하고 유효하다면 예약 페이지로 전송
 */
function onReserveClicked() {
	
	var isValid = validateInput(0);
	isValid = validateInput(1) && isValid;
	isValid = validateInput(2) && isValid;
	
	if (isValid) {
		postReserve();
	}
}

/**
 * 예약 버튼을 눌렀을 때 정보가 유효하다고 결정했다면 hidden input form에 정보를 담아서 서버에 전달하고 결과를 출력
 */
function postReserve() {
	var ticketInputs = document.querySelectorAll('.section_booking_ticket input');

	var bookerInputs = document.querySelectorAll('.form_horizontal input');
	var bookerName = bookerInputs[0].value;
	var bookerTelephone = bookerInputs[1].value;
	var bookerEmail = bookerInputs[2].value;

	var reserveForm = document.querySelector('.reserve_form');
	var reserveInputs = reserveForm.querySelectorAll('input');
		
	var priceInfoArray = new Array();
	for (var i = 0; i < ticketInputs.length; i++) {
		var isContainTicket = (ticketInputs[i].value > 0);
		if (isContainTicket) {
			var type = ticketInputs[i].getAttribute('pricetype');
			var count = ticketInputs[i].value;
			priceInfoArray.push(new PriceInfo(type, count));
		}
	}

	reserveInputs[0].setAttribute("value", bookerName);
	reserveInputs[1].setAttribute("value", bookerTelephone);
	reserveInputs[2].setAttribute("value", bookerEmail);
	reserveInputs[3].setAttribute("value", getUrlParameter('id'));
	reserveInputs[4].setAttribute("value", JSON.stringify(priceInfoArray));

	reserveForm.submit();
}

function initInputChangeEvent(){
	var bookerInputWrap = document.querySelector('.form_horizontal');
	bookerInputWrap.addEventListener('change',function(evt){
		var changedInput = evt.target;
		if(changedInput.tagName === 'INPUT'){
			validateInput(parseInt(changedInput.getAttribute('idx')));
		}
	});
}

// 관람시간의 설명 부분 줄 바꿈 처리
function initDescriptionSort(){
	var displayScheduleArea = document.querySelectorAll('.store_details .dsc')[1];
	displayScheduleArea.innerText = displayScheduleArea.innerText.replace(/ - /g,'\r\n- ');
}

// 클릭 이벤트 등록을 전담
function initClickEvents() {
	initTicketClickEvents();
	initAgreementClickEvents();
	initReserveClickEvents();
	initBackClickEvents();
}

document.addEventListener('DOMContentLoaded', function() {
	initPriceDescription();
	initClickEvents();
	initDescriptionSort();
	initDisplayTerm();
	initInputChangeEvent();
});