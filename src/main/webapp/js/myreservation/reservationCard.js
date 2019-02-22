function makeReservationCardHtml() {
	var reservationCardDatas = JSON.parse(document.getElementById("card_data").innerText);

	var bindTemplate = getBindTemplate("card");
	var innerHtml = makeHtmlFromListData(reservationCardDatas, bindTemplate);

	var ul = document.getElementsByClassName("list_cards")[0];
	ul.innerHTML += innerHtml;
}

function sendGetCardItemsAjax() {
	sendGetAjax("reservation_info", makeReservationCardItemHtml);
}

function makeReservationCardItemHtml(cardItemListMap) {
	makeConfirmedCard(cardItemListMap.confirmed);
	makeUsedCard(cardItemListMap.used);
	makeCancelCard(cardItemListMap.cancel);
	
	makeMoreButton(cardItemListMap);
	
	makeSummaryBoard(cardItemListMap.count);
}

function makeMoreButton(cardItemListMap) {
	var countList = cardItemListMap.count;

	countList.forEach((count) => {
		if (count.type == "confirmed") {
			if (count.count && cardItemListMap.confirmed && count.count > cardItemListMap.confirmed.length) {
				addMoreButton("confirmed");
			}
		} else if (count.type == "used") {
			if (count.count && cardItemListMap.used && count.count > cardItemListMap.used.length) {
				addMoreButton("used");
			}
		} else if (count.type == "cancel") {
			if (count.count && cardItemListMap.cancel && count.count > cardItemListMap.cancel.length) {
				addMoreButton("cancel");
			}
		}
	});
}

function addMoreButton(elementClassName) {
	var ul = document.getElementsByClassName(elementClassName)[0];
	var moreButton = document.getElementById("more_botton").innerHTML;
	ul.innerHTML += moreButton;
}

function makeSummaryBoard(countList) {
	var counts = document.querySelectorAll("span.figure");
	countList.forEach((count) => {
		if (count.type == "confirmed")	{
			counts[1].innerText = count.count
		} else if (count.type == "used")	{
			counts[2].innerText = count.count
		} if (count.type == "cancel")	{
			counts[3].innerText = count.count
		}
	});
	counts[0].innerText = counts[1].innerText*1 + counts[2].innerText*1 + counts[3].innerText*1;
}


function makeConfirmedCard(confirmedList) {
	var innerHtml;
	if (confirmedList.length) {
		var bindTemplate = getBindTemplate("card_item_confirmed");
		innerHtml = makeHtmlFromListData(confirmedList, bindTemplate);
	} else {
		innerHtml = document.getElementById("card_item_err").innerText;
	}

	var ul = document.getElementsByClassName("confirmed")[0];
	ul.innerHTML += innerHtml;
}

function makeUsedCard(usedList) {
	var innerHtml;
	if (usedList.length) {
		var bindTemplate = getBindTemplate("card_item_used");
		innerHtml = makeHtmlFromListData(usedList, bindTemplate);
	} else {
		innerHtml = document.getElementById("card_item_err").innerText;
	}

	var ul = document.getElementsByClassName("used")[0];
	ul.innerHTML += innerHtml;
}

function makeCancelCard(cancelList) {
	var innerHtml;
	if (cancelList.length) {
		var bindTemplate = getBindTemplate("card_item_cancel");
		innerHtml = makeHtmlFromListData(cancelList, bindTemplate);
	} else {
		innerHtml = document.getElementById("card_item_err").innerText;
	}

	var ul = document.getElementsByClassName("cancel")[0];
	ul.innerHTML += innerHtml;
}