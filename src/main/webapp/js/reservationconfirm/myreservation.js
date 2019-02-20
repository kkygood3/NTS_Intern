/**
 * @description : displayInfo ajax 요청
 */

var reservationInfoObject;
function myreservationInit(reservationInfo){
	reservationInfoObject = getReservationInfoObj(reservationInfo);
	reservationInfoObject.init();
	reservationInfoObject.render();
	
}

document.addEventListener("DOMContentLoaded", function() {
	myreservationInit(myreservation);
	document.querySelector("#reservation_receive").remove();
});