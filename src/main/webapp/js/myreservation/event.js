/**
 * 취소버튼 클릭이벤트
 * @returns
 */
function addCancelButtonClickEvent() {
	var area = document.querySelector(".card.confirmed");
	area.addEventListener("click", function(event){
		var cancelButton = event.target;
		if (cancelButton.tagName != "BUTTON") {
			return;
		}
		var cardItem = cancelButton.closest(".card_item");
		var reservationId = cardItem.querySelector(".booking_number").innerText.replace("No.", "");
		var popup = document.querySelector("div.popup_booking_wrapper");
		
		displayPopup(popup, cardItem);
		
		popup.addEventListener("click", function(event){
			if (!event.target.closest("A").className) {
				return;
			}
			if (event.target.innerText == "예") {
				sendPutAjax("/reservation_info/" + reservationId, movoItemToCancelCard, cardItem);
			}
			closePopup(popup);
			this.removeEventListener("click", arguments.callee);
		});
	});
}

function displayPopup(popup, reservationItem) {
	popup.style.display = "block";
	popup.querySelector(".pop_tit span").innerText = reservationItem.querySelector(".tit").innerText;
	popup.querySelector(".pop_tit small").innerText = reservationItem.querySelector(".item_dsc").innerText;
}

function closePopup(popup) {
	popup.style.display = "none";
}

function movoItemToCancelCard(updateCount, cardItem) {
	var canceled = document.querySelector(".card.cancel");
	canceled.appendChild(cardItem);

	cardItem.querySelector("button").remove();

	var counts = document.querySelectorAll("span.figure");
	counts[1].innerText = counts[1].innerText -1;
	counts[3].innerText = counts[3].innerText*1 + 1;
	
	adjustErrorCard(cardItem, counts[1].innerText);
}

function adjustErrorCard(cardItem, count) {
	var err = document.querySelector(".cancel .err");
	cardItem.querySelector(".btn_goto_share").remove();
	if (err) {
		var confirmed = document.querySelector(".card.confirmed");
		if (confirmed.lastElementChild.className == "link_booking_details") {
			confirmed.appendChild(err);
		} else {
			err.remove();
		}
	}
	if (count == "0") {
		err = document.getElementById("card_item_err").innerText;
		document.querySelector(".card.confirmed").innerHTML += err;
	}
}
