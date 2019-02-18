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
	
	makeSummaryBoard(cardItemListMap);
}

function makeSummaryBoard(data) {
	var counts = document.querySelectorAll("span.figure");
	counts[1].innerText = data.confirmed.length;
	counts[2].innerText = data.used.length;
	counts[3].innerText = data.cancel.length;
	counts[0].innerText = counts[1].innerText*1 + counts[2].innerText*1 + counts[3].innerText*1;
}


function makeConfirmedCard(confirmedList) {
	var bindTemplate = getBindTemplate("card_item");
	var innerHtml = makeHtmlFromListData(confirmedList, bindTemplate);

	var ul = document.getElementsByClassName("confirmed")[0];
	ul.innerHTML += innerHtml;
}

function makeUsedCard(usedList) {
	var bindTemplate = getBindTemplate("card_item");
	var innerHtml = makeHtmlFromListData(usedList, bindTemplate);

	var ul = document.getElementsByClassName("used")[0];
	ul.innerHTML += innerHtml;
}

function makeCancelCard(cancelList) {
	var bindTemplate = getBindTemplate("card_item");
	var innerHtml = makeHtmlFromListData(cancelList, bindTemplate);

	var ul = document.getElementsByClassName("cancel")[0];
	ul.innerHTML += innerHtml;
}