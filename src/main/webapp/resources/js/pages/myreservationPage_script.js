/**
 * Copyright 2019 NAVER Corp. All rights reserved. Except in the case of
 * internal use for NAVER, unauthorized use of redistribution of this software
 * are strongly prohibited.
 */

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

window.addEventListener("DOMContentLoaded", function () {
    myReservation.init();
});

var myReservation = {
    domElements: {
        totalFigure: document.querySelector("#_total_figure"),
        yetFigure: document.querySelector("#_yet_figure"),
        usedFigure: document.querySelector("#_used_figure"),
        cancelFigure: document.querySelector("#_cancel_figure"),

        sectionProcessing: document.querySelector(".card._processing"),
        sectionConfirmed: document.querySelector(".card.confirmed"),
        sectionUsed: document.querySelector(".card.used"),
        sectionCanceled: document.querySelector(".card.used.cancel"),
        
        sectionPlaceHolder : document.querySelector(".err"),
        
        cancelPopup: document.querySelector(".popup_booking_wrapper")
    },

    urls: {
        RESERVATION: "/reservation/api/reservations/",
    },

    templates: {
        reservationItem: document.querySelector("#rsvCardItem").innerHTML
    },

    state: {
        reservation_data: "",
    },

    init: function () {
        this.initDomWatcher();
        this.fetchData();
    },

    fetchData: function () {
        let request = new XhrRequest("GET", this.urls.RESERVATION)
        request.setCallback((respText) => {
        	this.state.reservation_data = JSON.parse(respText);
        	this.renderReservations();
        });
        request.send();
    },

    renderReservations: function () {
        arrayToElementRenderer(this.state.reservation_data.reservations, this.domElements.sectionConfirmed, this.templates.reservationItem);
        let reservations = this.domElements.sectionConfirmed.querySelectorAll("article");

        reservations.forEach((item) => {
            new ReservationCard(item, this.state.reservation_data.reservations, this.domElements.cancelPopup);
        });
    },
    initDomWatcher : function () {
    	let mObserver = new MutationObserver(() => {
			let yet = this.domElements.sectionConfirmed.querySelectorAll("article").length;
	        let used = this.domElements.sectionUsed.querySelectorAll("article").length;
	        let canceled = this.domElements.sectionCanceled.querySelectorAll("article").length;
	        let total = yet + used + canceled;
	        
	        this.controlSectionDisplay(yet, this.domElements.sectionConfirmed);
	        this.controlSectionDisplay(used, this.domElements.sectionUsed);
	        this.controlSectionDisplay(canceled, this.domElements.sectionCanceled);
	        
	        if(total == 0){
	        	this.domElements.sectionPlaceHolder.style.display = "";
	        } else {
	        	this.domElements.sectionPlaceHolder.style.display = "none";
	        }
	        
	        this.domElements.yetFigure.innerText = yet
	        this.domElements.usedFigure.innerText = used
	        this.domElements.cancelFigure.innerText = canceled
	        this.domElements.totalFigure.innerText = total;  
    	});
    	let config = { attributes: true, childList: true, characterData: true };
    	mObserver.observe(this.domElements.sectionConfirmed, config);
    	mObserver.observe(this.domElements.sectionUsed, config);
    	mObserver.observe(this.domElements.sectionCanceled, config);
    },
    
    controlSectionDisplay : function(value, target){
    	if(value == 0){
    		target.style.display = "none";
        } else {
        	target.style.display = "";
        }
    }
}
