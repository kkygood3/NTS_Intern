/**
 * 
 */
// library mapping to use forEach
HTMLCollection.prototype.forEach = Array.prototype.forEach;
NodeList.prototype.forEach = Array.prototype.forEach;

function ReservationCard(_reservation, reservationData, _popup) {
	this.reservation = _reservation
	this.cancelButton = _reservation.querySelector(".btn");
	this.cancelButtonWrapper = _reservation.querySelector(".booking_cancel");
	this.ReservationId = _reservation.dataset.id;
	this.reservationDesc = ""; 
	this.reservationDate = "";
	
	this.totalFigure = domElements.totalFigure;
	this.yetFigure = domElements.yetFigure;
	this.usedFigure = domElements.usedFigure;
	this.cancelFigure = domElements.cancelFigure;
	popup = _popup;
	// search for corresponding data;
	for (var key in reservationData) {

		if (reservationData.hasOwnProperty(key)) {
			if(reservationData[key].id == this.ReservationId){
				this.reservationDesc = reservationData[key].displayInfo.productDescription;
				this.reservationDate = reservationData[key].reservationDate.split(" ")[0];
			}
		}
	}
	
	if(this.reservation.dataset.cancelFlag == "true"){
		domElements.sectionCanceled.appendChild(this.reservation);
		this.cancelButtonWrapper.style.display = "none";
	} else {
		this.cancelButton.addEventListener("click",()=>{
			this.cancelAlert();
		});
	}
	this.renderPageDetails();
}

ReservationCard.prototype.cancelAlert = function(){
	var event = (e) => {
		if(e.target.classList.contains("_close_control")){
			popup.style.display = "none";
			popup.removeEventListener("click",event);
		} else if(e.target.classList.contains("_process_control")){
			popup.removeEventListener("click",event);
			this.cancelProcess();
		}
	};
	
	popup.querySelector(".pop_tit span").innerHTML = this.reservationDesc;
	popup.querySelector(".pop_tit .sm").innerHTML = this.reservationDate;
	popup.addEventListener("click", event);
	popup.style.display="block";
}

ReservationCard.prototype.cancelProcess = function(){
	let id = this.ReservationId;
	
	xhrPutRequest("/reservation/api/reservations/"+id, null, () => {
		alert("\""+this.reservationDesc+ "\"에 대한 예약이 취소되었습니다.")
		domElements.sectionCanceled.appendChild(this.reservation);
		this.cancelButtonWrapper.style.display = "none";
	},false);
	popup.style.display="none";
	this.renderPageDetails();
}

ReservationCard.prototype.renderPageDetails = function(){
	let yet = domElements.sectionConfirmed.querySelectorAll("article").length;
	let used = domElements.sectionUsed.querySelectorAll("article").length;
	let canceled = domElements.sectionCanceled.querySelectorAll("article").length;
	let total = yet+used+canceled;
	
	this.yetFigure.innerText = yet
	this.usedFigure.innerText = used
	this.cancelFigure.innerText = canceled
	this.totalFigure.innerText = total;
}


