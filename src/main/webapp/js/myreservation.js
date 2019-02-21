document.addEventListener("DOMContentLoaded", function() {
	myReservationPage.getMyReservationPage();
});

var myReservationPage = {
	getMyReservationPage: function(){
		this.reservationCounts.availableCnt.innerHTML = this.reservationList.availableList.querySelectorAll(".card_item").length;
		this.reservationCounts.usedCnt.innerHTML = this.reservationList.usedList.querySelectorAll(".card_item").length;
		this.reservationCounts.canceldCnt.innerHTML = this.reservationList.canceledList.querySelectorAll(".card_item").length;
		
		totalCnt.innerHTML = Number(this.reservationCounts.availableCnt.innerHTML)
				+ Number(this.reservationCounts.usedCnt.innerHTML)
				+ Number(this.reservationCounts.canceldCnt.innerHTML);

		this.reservationCancelPopup.showPopup();
		this.reservationCancelPopup.closePopup();

		this.setEvent.preventLink();
		this.setEvent.setReservationCancelEvent();
		this.setEvent.setReviewWritePageLink();

		addScrollTopEvent(this.elements.btnTop);
	},
	
	reservationList: {
		availableList : document.querySelector(".card.confirmed"),
		usedList : document.querySelector(".card.used"),
		canceledList : document.querySelector(".card.used.cancel"),
	},
	
	reservationCounts: {
		availableCnt : document.querySelector("#availableCnt"),
		usedCnt : document.querySelector("#usedCnt"),
		canceldCnt : document.querySelector("#canceldCnt"),
		totalCnt : document.querySelector("#totalCnt")
	},
	
	elements: {
		btnMyReservation : document.querySelector(".btn_my"),
		popupElement : document.querySelector(".popup_booking_wrapper"),
		btnListPopupClose : document.querySelectorAll(".popup_close"),
		btnCancel : document.querySelector("#reservation_cancle_btn"),
		btnTop : document.querySelector(".lnk_top")
	},
	
	ajaxSender : new AjaxSender(),
	
	ajaxOption : function(){
		var options = {
			contentType : "application/json",
			callback : function(){
				var reservationInfoId = document.querySelector(".popup_booking_wrapper").dataset.reservationInfoId;

				availableCnt.innerHTML = Number(availableCnt.innerHTML) - 1;
				canceldCnt.innerHTML = Number(canceldCnt.innerHTML) + 1;

				document.querySelector("#reservation_number_"+reservationInfoId).querySelector(".booking_cancel").style.display = "none";
				document.querySelector(".card.used.cancel").appendChild(document.querySelector("#reservation_number_"+reservationInfoId));
			}
		}
		
		return options;
	},
	
	cancelReservation: function(){
		var reservationInfoId = this.myReservationPage.elements.popupElement.dataset.reservationInfoId;
		
		this.myReservationPage.ajaxSender.sendPut("/reservation/api/reservations/" + reservationInfoId, this.myReservationPage.ajaxOption());
	}.bind(this),
	
	reservationCancelPopup: {
		showPopup : function(){
			this.myReservationPage.reservationList.availableList.addEventListener("click", function(event){
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
				this.myReservationPage.elements.popupElement.querySelector(".pop_tit").innerHTML = btnCancel.dataset.title;
				this.myReservationPage.elements.popupElement.dataset.reservationInfoId = btnCancel.dataset.reservationInfoId;
				this.myReservationPage.elements.popupElement.style.display = "block";
			}.bind(this));
		}.bind(this),

		closePopup : function(){
			this.myReservationPage.elements.btnListPopupClose.forEach(function(btnClose){
				btnClose.addEventListener("click", function(event){
					event.preventDefault();

					this.myReservationPage.elements.popupElement.style.display = "none";
				}.bind(this));
			}.bind(this));
		}.bind(this)
	},
	
	setEvent: {
		preventLink : function(){
			this.myReservationPage.elements.btnMyReservation.addEventListener("click", function(event){
				event.preventDefault();
			});
		}.bind(this),
		
		setReservationCancelEvent : function(){
			this.myReservationPage.elements.btnCancel.addEventListener("click", this.myReservationPage.cancelReservation);
		}.bind(this),

 		setReviewWritePageLink : function(){
			this.myReservationPage.reservationList.usedList.addEventListener("click", function(event){
				var btnReviewWrite;
				
				event.preventDefault();
				
				if(event.target.classList.contains("btn_review_write")){
					btnReviewWrite = event.target;
				} else if(event.target.parentNode.classList.contains("btn_review_write")) {
					btnReviewWrite = event.target.parentNode;
				} else {
					return;
				}
				
				window.location = "reviewWrite/" + btnReviewWrite.dataset.reservationInfoId;
			});
		}.bind(this)
	}
}