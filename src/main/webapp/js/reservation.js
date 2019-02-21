let bookerName = "duikPark";
let bookerTelephone = "010-8232-7431";
let bookerEmail = "duikpark@naver.com";
let displayInfoId = 1;
let reservationDate = "2019-02-21 11:15:03";
let priceInfoList = new Array();
priceInfoList.push(new ReservationPriceInfo("A", 1));

// getUrlParameter('displayInfoId') 대신 displayInfoId
let reserveRequest = JSON.stringify(new ReserveRequest(bookerName,bookerTelephone,bookerEmail,displayInfoId,reservationDate,priceInfoList));

requestAjax(postResponseHandler, 'api/reservations', 'POST', reserveRequest);

function requestAjax(callback, url, method, param) {
	if (!method) {
		method = 'GET';
	}
	var ajaxReq = new XMLHttpRequest();
	ajaxReq.callback = callback;
	ajaxReq.addEventListener('load', function(evt) {
		this.callback(evt.target.response);
	});
	ajaxReq.open(method, url);
	ajaxReq.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
	ajaxReq.responseType = 'json';
	ajaxReq.send(param)
}

function postResponseHandler(response){
}

function ReserveRequest(name, telephone, email, displayInfoId, reservationDate, priceInfoList){
	this.reservationName = name;
	this.reservationTel = telephone;
	this.reservationEmail = email;
	this.displayInfoId = parseInt(displayInfoId);
	this.reservationDate = reservationDate;
	this.reservationPriceInfoList = priceInfoList;
}

function ReservationPriceInfo(type, count){
	this.type = type;
	this.count = count;
}