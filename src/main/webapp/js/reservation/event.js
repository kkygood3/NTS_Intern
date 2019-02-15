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

function test() {
	var inputs = document.querySelectorAll(".section_booking_form input");
	var userInput = userInputData();
	for (var i = 0, len = inputs.length; i < len; i++) {
		inputs[i].addEventListener("click", function(event){
		});
	}
}
