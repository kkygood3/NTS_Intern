document.addEventListener('DOMContentLoaded', function() {
	requestAjax(loadReservationResponse, 'api/reviewWrite/displayInfo?reservationInfoId=' + {reservationInfoId},'GET');
});

function requestAjax(callback, url, method) {
	var ajaxReq = new XMLHttpRequest();
	ajaxReq.callback = callback;
	ajaxReq.addEventListener('load', function(evt) {
		this.callback(evt.target.response);
	});
	ajaxReq.open(method, url);
	ajaxReq.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
	ajaxReq.responseType = 'json';
	ajaxReq.send()
}

function loadReservationResponse(response) {
	console.log(response);
}
