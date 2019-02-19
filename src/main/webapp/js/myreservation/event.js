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
		popup.style.display = "block";
		
		popup.querySelector(".pop_tit span").innerText = cardItem.querySelector(".tit").innerText;
		popup.querySelector(".pop_tit small").innerText = cardItem.querySelector(".item_dsc").innerText;
		
		popup.addEventListener("click", function(event){
			if (!event.target.closest("A").className) {
				return;
			}
			if (event.target.innerText == "예") {
				sendPutAjax("/reservation_info/" + reservationId, movoItemToCancelCard, cardItem);
			}
			popup.style.display = "none";
			this.removeEventListener("click", arguments.callee);
		});
	});
}

function movoItemToCancelCard(updateCount, cardItem) {
	var canceled = document.querySelector(".card.cancel");
	canceled.appendChild(cardItem);
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
	cardItem.querySelector("button").remove();

	var counts = document.querySelectorAll("span.figure");
	counts[1].innerText = counts[1].innerText -1;
	counts[3].innerText = counts[3].innerText*1 + 1;
	
	if (counts[1].innerText == "0") {
		err = document.getElementById("card_item_err").innerText;
		document.querySelector(".card.confirmed").innerHTML += err;
	}
}