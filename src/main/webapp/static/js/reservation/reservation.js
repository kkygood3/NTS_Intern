/**
 * @desc 전역 변수
 */
const sendAjax = require("../sendAjax");
const handlebarsFunction = require("../handlebarsFunction");
const addCommaUtil = require('../util/addCommaUtil');

/**
 * @desc 예약 class
 */
function Reservation() {
    this.email = this.getReservationEmail();
    this.init();
}

Reservation.prototype = {
    init() {
        this.getReservationInfos();
    },

    getReservationEmail() {
        return document.querySelector(".section_my").dataset.reservationemail;
    },

    /**
     * @desc 예약정보들 가져온뒤 셋팅
     */
    getReservationInfos() {
        const reservationSendHeader = {
            method: "GET",
            uri: "/api/reservations?reservationEmail=" + this.email
        };

        sendAjax(reservationSendHeader, "", reservationResponse => {

            this._setReservationCountAll(reservationResponse.size);
            this._setReservations(reservationResponse.reservations);
        });
    },

    /**
     * @desc 전체 갯수 셋팅
     * @param {Number} reservationCount 
     */
    _setReservationCountAll(reservationCount) {
        document.querySelector("#count_all").innerHTML = reservationCount;
    },
    
    /**
     * @desc 취소 개수 셋팅
     * @param {Number} reservationCount 
     */
    _setCancelReservationCount(reservationCount){
    	document.querySelector("#count_cancel").innerHTML = reservationCount;
    },
    
    /**
     * @desc 예약 (아직 보지않은) 갯수 셋팅
     * @param {Number} reservationCount 
     */
    _setConfirmReservationCount(reservationCount){
    	document.querySelector("#count_confirm").innerHTML = reservationCount;
    },

    /**
     * @desc 예약 정보들 셋팅
     * @param {JSON} reservations 
     */
    _setReservations(reservations) {
        
        const confirmDiv = document.querySelector(".confirmed");
        const cancelDiv = document.querySelector(".cancel");
        
        if(reservations.length === 0 ){
        	
        	const noData = 0;
        	
        	confirmDiv.remove();
        	cancelDiv.remove();
        	
        	this._setCancelReservationCount(noData);
        	this._setConfirmReservationCount(noData);
        	
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
        
        this._setCancelReservationCount(cancelCount);
    	this._setConfirmReservationCount(confirmCount);

    }
};

/**
 * @desc 취소 관련 class
 */
function Cancel(){
    this.init();
}

Cancel.prototype = {
    init(){
        this.addCancelButtonEvent();
        this.addCancelPopUpCloseButtonEvent();
        this.addCancelButtonNoButtonEvent();
    },

    /**
     * @desc 취소하기 버튼 이벤트
     */
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

    /**
     * @desc 취소하기 팝업 X 버튼 이벤트
     */
    addCancelPopUpCloseButtonEvent(){
        document.querySelector(".popup_btn_close").addEventListener("click", event => {
            const popupCloseButton = event.currentTarget;
            const popupParent = popupCloseButton.parentNode.parentNode;

            popupParent.classList.remove("open");
            popupParent.classList.add("hide");

        });
    },
    
    /**
     * @desc 취소하기 예 버튼 이벤트
     * @param {DOMElement} bookingNumberElement 
     */
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
    
    /**
     * @desc 취소하기 아니오 버튼 이벤트
     */
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
