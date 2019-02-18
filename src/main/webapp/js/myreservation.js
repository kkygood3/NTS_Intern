document.addEventListener("DOMContentLoaded", function() {
	getMyReservationPage();
});

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
	scrollTopEvent(document.querySelector(".lnk_top"))
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
			var callBack = function(){
				document.querySelector("#avilableCnt").innerHTML = Number(document.querySelector("#avilableCnt").innerHTML) - 1;
				document.querySelector("#canceldCnt").innerHTML = Number(document.querySelector("#canceldCnt").innerHTML) + 1;

				document.querySelector("#reservation_number_"+reservationInfoId).querySelector(".booking_cancel").style.display = "none";
				document.querySelector(".card.used.cancel").appendChild(document.querySelector("#reservation_number_"+reservationInfoId));
			}
			
			event.preventDefault();

			if(btn.id === "reservation_cancle_btn"){
				ajaxSend("PUT", "api/reservations/" + reservationInfoId, callBack, "application/json")
			}
			
			document.querySelector(".popup_booking_wrapper").style.display = "none";
		});
	});
}