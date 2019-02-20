function reservationDisplayInfoRequest(callBack, displayInfoId){
	
	var request = {
			method:"GET",
			contentType:"application/x-www-form-urlencoded",
			queryString:""
	}
	var requestUri = "/api/reservations/" + displayInfoId;
	sendRequest(request, requestUri, callBack);
}

function reservationDisplayInfoInit(reservationDisplayInfo){
	var reservationDisplayInfoObject = getReservationDisplayInfoObj(reservationDisplayInfo);
	reservationDisplayInfoObject.init();
	reservationDisplayInfoObject.render();
	
	document.addEventListener("change",()=>{
		validateReservationDisplayInfo(reservationDisplayInfoObject);
	});
	document.addEventListener("click",()=>{
		validateReservationDisplayInfo(reservationDisplayInfoObject);
	});
}

function validateReservationDisplayInfo(reservationDisplayInfoObject){
	if(reservationDisplayInfoObject.validation()){
		reservationDisplayInfoObject.reservationButton.enable();
	} else{
		reservationDisplayInfoObject.reservationButton.disable();
	}
}

document.addEventListener("DOMContentLoaded", ()=>{
	var displayInfoId = document.location.search.split("=")[1];
	reservationDisplayInfoRequest(reservationDisplayInfoInit, displayInfoId);
});