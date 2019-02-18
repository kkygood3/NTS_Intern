document.addEventListener("DOMContentLoaded", function() {
	getMyReservationPage();
});

function ajaxPut(reservationInfoId){
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
	document.querySelector("#avilableCnt").innerHTML = document.querySelector(".card.confirmed").querySelectorAll(".card_item").length;
	document.querySelector("#usedCnt").innerHTML = document.querySelector(".card.used").querySelectorAll(".card_item").length;
	document.querySelector("#canceldCnt").innerHTML = document.querySelector(".card.used.cancel").querySelectorAll(".card_item").length;
	
	document.querySelector("#totalCnt").innerHTML = Number(document.querySelector("#avilableCnt").innerHTML) 
												+ Number(document.querySelector("#usedCnt").innerHTML)
												+ Number(document.querySelector("#canceldCnt").innerHTML);
	
	if(document.querySelector("#totalCnt").innerHTML !== "0"){
		document.querySelector(".err").style.display = "none";
		document.querySelector(".wrap_mylist").style.display = "block";
	} else {
		document.querySelector(".wrap_mylist").style.display = "none";
		document.querySelector(".err").style.display = "block";
	}

	setEvent.preventLink();
	setEvent.scrollTop();
	setEvent.showCanclePopup();
	setEvent.cancelReservation();
}

function SetEvent(){}
SetEvent.prototype.preventLink = function(){
	document.querySelector(".btn_my").addEventListener("click", function(){
		event.preventDefault();
	});
}

SetEvent.prototype.scrollTop = function(){
	document.querySelector(".lnk_top").addEventListener("click", function(){
		event.preventDefault();
		document.documentElement.scrollTop = 0;
	});
}

SetEvent.prototype.showCanclePopup = function(){
	document.querySelector(".card.confirmed").addEventListener("click", function(event){
		var btnCancel;
		event.preventDefault();
		
		if(event.target.className === "btn"){
			btnCancel = event.target;
		} else if (event.target.className === "btn_text"){
			btnCancel = event.target.parentNode;
		}
		else {
			return;
		}
		document.querySelector(".popup_booking_wrapper").querySelector(".pop_tit").innerHTML = btnCancel.dataset.title;
		document.querySelector(".popup_booking_wrapper").dataset.reservationInfoId = btnCancel.dataset.reservationInfoId;
		document.querySelector(".popup_booking_wrapper").style.display = "block";
	});
}

SetEvent.prototype.cancelReservation = function(){
	document.querySelectorAll(".popup_close").forEach(function(btn){
		btn.addEventListener("click", function(event){
			var reservationInfoId = document.querySelector(".popup_booking_wrapper").dataset.reservationInfoId;
			var cancelList = document.querySelector(".card.used.cancel");
			event.preventDefault();

			if(btn.id === "reservation_cancle_btn"){
				document.querySelector("#avilableCnt").innerHTML = Number(document.querySelector("#avilableCnt").innerHTML) - 1;
				document.querySelector("#canceldCnt").innerHTML = Number(document.querySelector("#canceldCnt").innerHTML) + 1;
				
				cancelList.appendChild(document.querySelector("#reservation_number_"+reservationInfoId));
				
				ajaxPut(reservationInfoId);
			}
			
			document.querySelector(".popup_booking_wrapper").style.display = "none";
		});
	});
}