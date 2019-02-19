/**
 * @desc 전역 변수
 */
const sendAjax = require("../sendAjax");
const handlebarsFunction = require("../handlebarsFunction");
const addCommaUtil = require('../util/addCommaUtil');

function Reservation() {
    this.email = this.getReservationEmail();
    this.init();
}

Reservation.prototype = {
    init() {
        this.setReservationInfos();
    },

    getReservationEmail() {
        return document.querySelector(".section_my").dataset.reservationemail;
    },

    setReservationInfos() {
        const reservationSendHeader = {
            method: "GET",
            uri: "/api/reservations?reservationEmail=" + this.email
        };

        sendAjax(reservationSendHeader, "", reservationResponse => {

            this.setReservationCountAll(reservationResponse.size);
            this.setReservations(reservationResponse.reservations);
        });
    },

    setReservationCountAll(reservationCount) {
        document.querySelector("#count_all").innerHTML = reservationCount;
    },
    
    setCancelReservationCount(reservationCount){
    	document.querySelector("#count_cancel").innerHTML = reservationCount;
    },
    
    setConfirmReservationCount(reservationCount){
    	document.querySelector("#count_confirm").innerHTML = reservationCount;
    },

    setReservations(reservations) {
        
        const confirmDiv = document.querySelector(".confirmed");
        const cancelDiv = document.querySelector(".cancel");
        
        if(reservations.length === 0 ){
        	
        	const noData = 0;
        	
        	confirmDiv.remove();
        	cancelDiv.remove();
        	
        	this.setCancelReservationCount(noData);
        	this.setConfirmReservationCount(noData);
        	
        	return;
        }
        
        document.querySelector(".err").remove();
        
        let cancelCount = 0;
        let confirmCount = 0;
        
        reservations.forEach( reservation => {
            reservation.reservationDate = reservation.reservationDate.substr(0,10);
            reservation.totalPrice = addCommaUtil.getCommaToNumberString(reservation.totalPrice.toString());

            if(reservation.cancelYn === true){
                cancelDiv.innerHTML += handlebarsFunction.getHandlebarTemplateFromHtml("#reservation_cancel_template",{reservation: reservation});
                cancelCount++;
            } else {
                confirmDiv.innerHTML += handlebarsFunction.getHandlebarTemplateFromHtml("#reservation_template",{reservation: reservation});
                confirmCount++;
            }
        });
        
        this.setCancelReservationCount(cancelCount);
    	this.setConfirmReservationCount(confirmCount);

    }
};

function Cancel(){
    this.init();
}

Cancel.prototype = {
    init(){
        this.addCancelButtonEvent();
        this.addCancelPopUpCloseButtonEvent();
        this.addCancelButtonNoButtonEvent();
    },

    addCancelButtonEvent(){
        const cancelPopup = document.querySelector(".popup_booking_wrapper");
        const cancelButtonList = document.querySelectorAll(".cancel_button");

        cancelButtonList.forEach(cancelButton => {
            cancelButton.addEventListener("click", event => {
                const cardDetail = event.currentTarget.parentNode.parentNode;
                const title = cardDetail.querySelector(".tit").innerText;
                const reservationDate = cardDetail.querySelector(".reservation_date").innerText;

                const cancelInfo = document.querySelector(".pop_tit");
                cancelInfo.querySelector(".reservation_date").innerText = reservationDate;
                cancelInfo.querySelector(".product_name").innerText = title;

                cancelPopup.classList.remove("hide");
                cancelPopup.classList.add("open");

                let bookingNumberElement = event.currentTarget.parentNode.parentNode.querySelector(".booking_number");
                this.addCancelButtonYesButtonEvent(bookingNumberElement);
                
            });
        });
    },

    addCancelPopUpCloseButtonEvent(){
        document.querySelector(".popup_btn_close").addEventListener("click", event => {
            const popupCloseButton = event.currentTarget;
            const popupParent = popupCloseButton.parentNode.parentNode;

            popupParent.classList.remove("open");
            popupParent.classList.add("hide");

        });
    },
    
    addCancelButtonYesButtonEvent(bookingNumberElement){
        document.querySelector(".cancel_yes").addEventListener("click", event => {

            let reservationId = bookingNumberElement.innerText.substr(3);
            let cancelHeader = {
                method : "PUT",
                uri : "/api/reservations/" + reservationId
            };
    
            sendAjax(cancelHeader,'', ()=>{
                alert("주문하신 " + reservationId + " 번호의 내역이 한건 취소되었습니다.");
                location.reload();
            });
        });
    },
    
    addCancelButtonNoButtonEvent(){
        document.querySelector(".cancel_no").addEventListener("click", event => {
            const popupCloseButton = event.currentTarget;
            const popupParent = popupCloseButton.parentNode.parentNode.parentNode;

            popupParent.classList.remove("open");
            popupParent.classList.add("hide"); 
        });
    },

};

document.addEventListener("DOMContentLoaded", () => {
    const reservation = new Reservation();
    const cancel = new Cancel();
});
