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
	var cancel = document.querySelector(".card.cancel");
	cancel.appendChild(cardItem);

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

function addMoreButtonClickEvent() {
	var listWrapper = document.querySelector("div.wrap_mylist");
	listWrapper.addEventListener("click", function(event){
		var moreButton = event.target.closest("div");
		if (moreButton.className != "more") {
			return;
		}
		var li = moreButton.closest("li");
		var start = li.childElementCount - 2;
		sendGetAjax("reservation_info/" + li.id + "?start=" + start, addReservationCardItem);
	});
}

function addReservationCardItem(cardItemList) {
	
	var ul = document.getElementById(cardItemList.type);
	
	ul.removeChild(ul.lastElementChild);
	
	var innerHtml;
	var bindTemplate = getBindTemplate("card_item_" + cardItemList.type);
	innerHtml = makeHtmlFromListData(cardItemList.reservationItems, bindTemplate);
	
	var li = document.getElementsByClassName(cardItemList.type)[0];
	li.innerHTML += innerHtml;
	
	if (li.childElementCount - 1 < getReservationCount(cardItemList.type)) {
		makeMoreButton(cardItemListMap);
	}
}

function getReservationCount(type) {
	if (type == "confirmed") {
		return document.querySelectorAll(".summary_board span")[1].innerText * 1;
	} else if (type == "used") {
		return document.querySelectorAll(".summary_board span")[2].innerText * 1;
	} else if (type == "cancel") {
		return document.querySelectorAll(".summary_board span")[3].innerText * 1;
	}
}
