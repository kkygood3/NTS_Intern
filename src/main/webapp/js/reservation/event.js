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
		if (button.getAttribute("title") == "더하기") {
			minusButton.classList.remove("disabled");
			count.value = count.value * 1 + 1;
			
		} else if (button.getAttribute("title") == "빼기") {
			var disabled = false;
			if (count.value == 0) {
				return;
			}
			count.value = count.value * 1 - 1;
			if (count.value == 0) {
				minusButton.classList.add("disabled");
			}
		}
	});
}

function addBookingFormInputChangeEvent() {
	var inputs = document.querySelectorAll(".section_booking_form input");
	for (var i = 0, len = inputs.length; i < len; i++) {
		inputs[i].addEventListener("change", function(event){
			var value = event.target.value;
			
			var emailValue = document.querySelector("[name='email']").value;
		    var bValid = (/^[\w+_]\w+@\w+\.\w+$/).test(emailValue);
			if (event.target.id == "name") {
				if ((/^([가-힣]{2,})|(([A-Z][a-z]*\s)+[A-Z][a-z]*)$/).test(value)) {
					return;
				}
				console.log("잘못된 이름");
			} else if (event.target.id == "tel") {
				if ((/^01[016-9]-[0-9]{3,4}-[0-9]{4}$/).test(value)) {
					return;
				} else if (value.match(/^01[01(6-9)][0-9]{7,8}$/)) {
					var midLength = value.length == 11 ? 4 : 3;
					event.target.value = value.substr(0, 3) + "-" + value.substr(3, midLength) + "-" + value.substr(3 + midLength, 4);
					return;
				}
				console.log("잘못된 전화번호");
			} else if (event.target.id == "email") {
				if ((/^[a-z][-\.\w]*@[a-z][-\.\w]*\.[a-z]{2,3}$/i).test(value)) {
					return;
				}
				console.log("잘못된 메일");
			}
		});
	}
}


function addBookingButtonClickEvent() {
	var bookingButton = document.getElementsByClassName("bk_btn")[0];
	bookingButton.addEventListener("click", function(event){
		var reservationForm = document.querySelector("form.form_horizontal");
		var reservationData = {};
		reservationData.productId = displayInfo().productId;
		reservationData.name = document.getElementById("name").value;
		reservationData.tel = document.getElementById("tel").value;
		reservationData.email = document.getElementById("email").value	;
		reservationData.price = makePriceData();
		console.log(JSON.stringify(reservationData));
		$.ajax({
			method: "POST",
			url: "/detail/" + displayInfo().displayInfoId + "/reservation",
			data: JSON.stringify(reservationData),
			dataType: "json",
			contentType : "application/json"
		});
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
//		priceData.price = price;
		priceData.count = count;
		priceDatas.push(priceData);
	}
	return priceDatas;
}
