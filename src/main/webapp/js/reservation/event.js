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

function addPlusMiusButtonClickEvent() {
	var ticketBody = document.getElementsByClassName("ticket_body")[0];

	ticketBody.addEventListener("click", function(event){
		var buttonHolder = event.target.closest(".qty");
		var minusButton = buttonHolder.getElementsByClassName("btn_plus_minus")[0];
		var button = event.target;
		if (button.tagName != "A") {
			return;
		}
		
		var count = buttonHolder.getElementsByClassName("count_control_input")[0];
		var totalCount = document.getElementById("totalCount");
		if (button.getAttribute("title") == "더하기") {
			minusButton.classList.remove("disabled");
			count.value = count.value * 1 + 1;
			totalCount.innerText = totalCount.innerText * 1 + 1;
			
		} else if (button.getAttribute("title") == "빼기") {
			var disabled = false;
			if (count.value == 0) {
				return;
			}
			count.value = count.value * 1 - 1;
			if (count.value == 0) {
				minusButton.classList.add("disabled");
			}
			totalCount.innerText = totalCount.innerText * 1 - 1;
		}
		setReservationButtonDisable();
		var total = buttonHolder.querySelector(".total_price");
		var price = buttonHolder.querySelector(".price").innerText;
		total.innerText = count.value * price;
	});
}

function setReservationButtonDisable() {
	var bkButton = document.querySelector(".box_bk_btn .bk_btn_wrap");
	if (isValidAllReservationInputs()) {
		bkButton.classList.remove("disable");
	} else {
		bkButton.classList.add("disable");
	}
}

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
			var regExp;
			if (event.target.id == "name") {
				regExp = regex.name;
			} else if (event.target.id == "tel") {
				value = adjustTel(value);
				event.target.value = value;
				regExp = regex.tel;
			} else if (event.target.id == "email") {
				regExp = regex.email;
			}
			setReservationButtonDisable();
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

function imformWarning(element) {
	element.classList.add("warning");
}

function agreed() {
	return document.getElementById("chk3").checked
}

function existCountOverZero() {
	var priceInfos = document.querySelectorAll(".ticket_body .qty");
	for (var i = 0, len = priceInfos.length; i < len; i++) {
		var count = priceInfos[i].getElementsByClassName("count_control_input")[0].value;
		if (count > 0) return true;
	}
	return false;
}

function addBookingButtonClickEvent() {
	var bookingButton = document.getElementsByClassName("bk_btn")[0];
	bookingButton.addEventListener("click", function(event){
		if (!isValidAllReservationInputs()) {
			console.log("disable");
			return;
		}
		var reservationForm = document.querySelector("form.form_horizontal");
		var reservationData = {};
		reservationData.productId = displayInfo().productId;
		reservationData.name = document.getElementById("name").value;
		reservationData.tel = document.getElementById("tel").value;
		reservationData.email = document.getElementById("email").value;
		reservationData.price = makePriceData();
		
		var form = document.getElementById("user_reservation_input");
		var input = form.querySelector("input");
		input.value = JSON.stringify(reservationData);
		form.submit();
	});
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
