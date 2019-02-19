function ReservePrice(price, priceTypeName) {
	this.price = price.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
	this.label = getTypeLabel(priceTypeName);
}

// 티켓 수량 증가, 감소 클릭 이벤트 등록
function setTicketClick() {
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
	
						var isMinus = (clickedTag.classList.contains('ico_minus3') && amountValue > 0);
						var isPlus = (clickedTag.classList.contains('ico_plus3'));
	
						var item = clickedTag.parentElement.parentElement.parentElement;
						var cost = item.getAttribute('cost');
						var itemPriceArea = item.querySelector('.total_price');
						
						if (isMinus) {
							if (amountValue == 1) {
								btnMinus.classList.add('disabled');
								amountArea.classList.add('disabled');
								itemPriceArea.parentElement.classList.remove('on_color');
							}
							
							itemPriceArea.innerText = (cost * (amountValue - 1)).toLocaleString();
							amountArea.setAttribute('value', amountValue - 1);
	
							ticketTotalArea.innerText = parseInt(ticketTotalArea.innerText) - 1;
						} else if (isPlus) {
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

function setAgreementDesciptionClick() {
	// 약관 보기 버튼
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

// 예약하기 버튼 활성화 가능 여부 체크
function setReserveClick() {
	var reserveBtn = document.querySelector('.bk_btn_wrap');

	var ticketInputsWrap = document.querySelector('.section_booking_ticket');
	var bookerInputsWrap = document.querySelector('.section_booking_form');

	var ticketInputs = ticketInputsWrap.querySelectorAll('.ticket_body input');
	var bookerInputs = bookerInputsWrap.querySelectorAll('.form_horizontal input');

	var agreementCheckBox = document.querySelector('#chk3');

	ticketInputsWrap.addEventListener('click', checkAvailableReserve);
	bookerInputsWrap.addEventListener('change', checkAvailableReserve);

	/**
	 * 예매하기 버튼을 클릭할 수 있는지 체크. 유효성 검증은 버튼 클릭시 하므로 버튼 활성/비활성화만 검증한다.
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
			reserveBtn.classList.remove('disable');
			reserveBtn.addEventListener('click', onReserveClicked);
		} else {
			// 예약하기 버튼 사용 불가
			reserveBtn.classList.add('disable');
			reserveBtn.removeEventListener('click', onReserveClicked);
		}

		return isAvailableBtn;
	}
}

// 클릭 이벤트 등록을 전담
function initClickEvents() {
	setTicketClick();
	setAgreementDesciptionClick();
	setReserveClick();
	setPageBackClick();
}

function setPageBackClick() {
	document.querySelector('.btn_back').setAttribute('href',
			'detail?id=' + getUrlParameter('id'));
}
/**
 * 예약하기 버튼을 클릭했을때 이벤트 예약자 정보를 검증하고 이상이 없다면 예약 페이지로 이동한다.
 */
function onReserveClicked() {
	var bookerInputs = document.querySelectorAll('.form_horizontal input');
	var warningAreas = document.querySelectorAll('.warning_msg');

	var bookerName = bookerInputs[0].value;
	var bookerTelephone = bookerInputs[1].value;
	var bookerEmail = bookerInputs[2].value;

	// 틀린 영역에 표시하는데 사용할 boolean Array
	var validationArr = new Array(bookerInputs.length);
	for (var i = 0; i < validationArr.length; i++) {
		validationArr[i] = true;
	}

	var isValidName = (bookerName.length > 1);
	var isValidTelephone = REG_TELEPHONE.test(bookerTelephone);
	var isValidEmail = REG_EMAIL.test(bookerEmail);

	if (!isValidName) {
		validationArr[0] = false;
	}

	if (!isValidTelephone) {
		validationArr[1] = false;
	}

	if (!isValidEmail) {
		validationArr[2] = false;
	}

	/**
	 * 전부 TRUE이면 예약 페이지로 이동 하나라도 FALSE이면 형식이 틀렸다는 메세지 출력
	 */
	var isValid = true;
	for (var i = 0; i < validationArr.length; i++) {
		isValid = isValid && validationArr[i];

		if (!validationArr[i]) {
			warningAreas[i].style.visibility = 'visible';
			(function(index) {
				setTimeout(function() {
					warningAreas[index].style.visibility = 'hidden';
				}, 1000)
			})(i);
		}
	}

	if (isValid) {
		postReserve();
	}
}

function PriceInfo(type, count) {
	this.type = type;
	this.count = count;
};

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

document.addEventListener('DOMContentLoaded', function() {
	initPriceDescription();

	initClickEvents();
});