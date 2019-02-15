document.addEventListener("DOMContentLoaded", function() {
	getMyReservationPage();
});

function changeCancelFlag(reservationInfoId){
	var httpRequest;
	if (window.XMLHttpRequest) {
		httpRequest =  new XMLHttpRequest();
		
		httpRequest.onreadystatechange = function() {
			if (httpRequest.readyState === 4 && httpRequest.status === 200) {
				console.log("success");
			}
		}
		
		httpRequest.open("PUT", "api/reservations/" + reservationInfoId);
		httpRequest.setRequestHeader("Content-type", "application/json");
		httpRequest.send();
	}
}

function getMyReservationPage(){
	this.setEvent = new SetEvent();
	
	document.querySelector("#totalCnt").innerHTML = Number(document.querySelector("#avilableCnt").innerHTML) 
												+ Number(document.querySelector("#alreadyCnt").innerHTML)
												+ Number(document.querySelector("#canceldCnt").innerHTML);
	
	if(document.querySelector("#totalCnt").innerHTML !== "0"){
		document.querySelector(".err").style.display = "none";
		document.querySelector(".wrap_mylist").style.display = "block";
	} else {
		document.querySelector(".wrap_mylist").style.display = "none";
		document.querySelector(".err").style.display = "block";
	}
	
	setEvent.scrollTop();
	setEvent.cancelReservation();
}

function SetEvent(){}

SetEvent.prototype.scrollTop = function(){
	document.querySelector(".lnk_top").addEventListener("click", function(){
		event.preventDefault();
		document.documentElement.scrollTop = 0;
	});
}

SetEvent.prototype.cancelReservation = function(){
	document.querySelector(".card.confirmed").addEventListener("click", function(event){
		event.preventDefault();
		if(event.target.className === "btn"){
			changeCancelFlag(event.target.dataset.reservationInfoId);
		}
	});
}