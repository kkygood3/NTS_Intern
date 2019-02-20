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
		var button = event.target;
		if (button.tagName != "A") {
			return;
		}
		
		var buttonHolder = event.target.closest(".qty");
		var minusButton = buttonHolder.getElementsByClassName("btn_plus_minus")[0];
		var count = buttonHolder.getElementsByClassName("count_control_input")[0];
		var totalCount = document.getElementById("totalCount");
		
		if (button.getAttribute("title") == "더하기") {
			increaseCount(minusButton, count, totalCount);
		} else if (button.getAttribute("title") == "빼기") {
			if (decreaseCount(minusButton, count, totalCount)) {
				return;
			}
		}
		setBookingButtonDisable();
		setTotalPrice(buttonHolder, count.value);
	});
}

function increaseCount(minusButton, count, totalCount) {
	minusButton.classList.remove("disabled");
	count.value = count.value * 1 + 1;
	totalCount.innerText = totalCount.innerText * 1 + 1;
	
}

function decreaseCount(minusButton, count, totalCount) {
	if (count.value == 0) {
		return false;
	}
	count.value = count.value * 1 - 1;
	if (count.value == 0) {
		minusButton.classList.add("disabled");
	}
	totalCount.innerText = totalCount.innerText * 1 - 1;
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
	bookingButton.style.cursor = "default";
	bookingButton.addEventListener("click", function(event){
		if (!isValidAllReservationInputs()) {
			return;
		}
		var reservationData = makeJsonReservationData();

		$.ajax({
			method: "POST",
			url: "/product/" + displayInfo().displayInfoId + "/reservation",
			data: JSON.stringify(reservationData),
			dataType: "json",
			contentType : "application/json",
			success: function(data){
				if (data) {
					window.location.href = "/detail/" + displayInfo().displayInfoId;
				} else {
					alert("잘못된 입력입니다.");
				}
		    }
		});
	});
}

function makeJsonReservationData() {
	var reservationData = {};
	reservationData.productId = displayInfo().productId;
	reservationData.name = document.getElementById("name").value;
	reservationData.tel = document.getElementById("tel").value;
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
