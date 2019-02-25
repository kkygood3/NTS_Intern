/**
 * 약관 더보기 클릭이벤트
 */
function addagreementClickEvent() {
	var agreements = document.getElementsByClassName("agreement");

	for (let agreement of agreements) {
		agreement.addEventListener("click", function(event){
			if (!event.target.closest("a")) {
				return;
			}
			if (hasClass(event.currentTarget, "open")) {
				event.currentTarget.classList.remove("open");
			} else {
				event.currentTarget.classList.add("open");
			}
		});
	}
}

function hasClass(element, classNameToFind) {
	for (let className of element.classList) {
		if (className == classNameToFind) {
			return true;
		}
	}
	return false;
}

/**
 * 티켓 수량 클릭이벤트
 */
function addPlusMiusButtonClickEvent() {
	var ticketBody = document.getElementsByClassName("ticket_body")[0];
	ticketBody.addEventListener("click", function(event){
		var target = event.target;
		if (target.tagName != "A") {
			return;
		}
		
		var buttonHolder = event.target.closest(".qty");
		var elements = {
			"buttons": buttonHolder.getElementsByClassName("btn_plus_minus"),
			"count": buttonHolder.getElementsByClassName("count_control_input")[0],
			"countText": buttonHolder.getElementsByClassName("individual_price")[0],
			"totalCount": document.getElementById("totalCount"),
			"maxCount": 10
		}
		
		if (target.getAttribute("title") == "더하기") {
			increaseCount(elements);
		} else if (target.getAttribute("title") == "빼기") {
			if (decreaseCount(elements)) {
				return;
			}
		}
		setBookingButtonDisable();
		setTotalPrice(buttonHolder, elements.count.value);
	});
}

function increaseCount(elements) {
	if (elements.count.value == elements.maxCount) {
		return false;
	}
	
	elements.count.value++;
	elements.totalCount.innerText++;
	
	if (elements.count.value == 1) {
		elements.buttons[0].classList.remove("disabled");
		elements.countText.style.color = "#000";
	} else if (elements.count.value == elements.maxCount) {
		elements.buttons[1].classList.add("disabled");
	}
}

function decreaseCount(elements) {
	if (elements.count.value == 0) {
		return false;
	}
	if (elements.count.value == 1) {
		elements.buttons[0].classList.add("disabled");
		elements.countText.style.color = "#bbb";
	} else if (elements.count.value == elements.maxCount) {
		elements.buttons[1].classList.remove("disabled");
	}
	elements.count.value--;
	elements.totalCount.innerText--;
}

function setBookingButtonDisable() {
	var bookingButton = document.getElementsByClassName("bk_btn")[0];
	var bookingButtonWrap = document.getElementsByClassName("bk_btn_wrap")[0]; 
	if (isValidAllReservationInputs()) {
		bookingButtonWrap.classList.remove("disable");
		bookingButton.style.cursor = "pointer";

	} else {
		bookingButtonWrap.classList.add("disable");
		bookingButton.style.cursor = "default";
	}
}

function setTotalPrice(buttonHolder, count) {
	var totalPrice = buttonHolder.querySelector(".total_price");
	var price = buttonHolder.querySelector(".price").innerText;
	totalPrice.innerText = count * price;
}

/**
 * 예약정보 입력 validation check
 */
function addBookingFormInputChangeEvent() {
	var regex = new RegularExpression();
	var inputs = document.querySelectorAll(".section_booking_form input");
	for (var i = 0, len = inputs.length; i < len; i++) {
		inputs[i].addEventListener("change", function(event){
			var value = event.target.value;
			if (!value) {
				event.target.classList.remove("warning");
				return;
			}
			if (event.target.id == "tel") {
				value = adjustTel(value);
				event.target.value = value
			}
			
			setBookingButtonDisable();

			if (event.target.id == "chk3") {
				return;
			}
			
			var regExp = regex.getRegularExpression(event.target.id);

			if (isValidReservationInput(value, regExp)){
				event.target.classList.remove("warning");
			} else {
				event.target.classList.add("warning");
			}
		});
	}
}

function adjustTel(tel) {
	if (tel.length != 10 && tel.length != 11) {
		return tel;
	}
	var midLength = tel.length == 11 ? 4 : 3;
	return tel.substr(0, 3) + "-" + tel.substr(3, midLength) + "-" + tel.substr(3 + midLength, 4);
}

function agreed() {
	return document.getElementById("chk3").checked;
}

/**
 * 예약버튼 클릭이벤트
 */
function addBookingButtonClickEvent() {
	var bookingButton = document.getElementsByClassName("bk_btn")[0];
	bookingButton.addEventListener("click", function(event){
		if (!isValidAllReservationInputs()) {
			return;
		}
		var reservationData = makeJsonReservationData();

		sendPostAjax("/api/product/" + displayInfo().productId + "/display/" + displayInfo().displayInfoId + "/reservation", reservationData, (data) => {
			if (data) {
				window.location.href = "/product/" + displayInfo().productId + "/display/" + displayInfo().displayInfoId;
			} else {
				alert("잘못된 입력입니다.");
			}
	    });
	});
}

function makeJsonReservationData() {
	var reservationData = {};
	reservationData.productId = displayInfo().productId;
	reservationData.name = document.getElementById("name").value;
	reservationData.telephone = document.getElementById("tel").value;
	reservationData.email = document.getElementById("email").value;
	reservationData.reservationDate = displayInfo().reservationDate;
	reservationData.price = makePriceData();
	return reservationData;
}

function makePriceData() {
	var priceDatas = [];
	var priceInfos = document.querySelectorAll(".ticket_body .qty");
	for (var i = 0, len = priceInfos.length; i < len; i++) {
		var count = priceInfos[i].getElementsByClassName("count_control_input")[0].value;
		var price = priceInfos[i].getElementsByClassName("price")[0].innerText;
		var priceData = {};
		priceData.productPriceId = priceInfos[i].id.replace("product_price_id_", "");
		priceData.count = count;
		if(count > 0) {
			priceDatas.push(priceData);
		}
	}
	return priceDatas;
}
