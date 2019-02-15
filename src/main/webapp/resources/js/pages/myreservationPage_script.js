/**
 * 
 */

/**
 * Copyright 2019 NAVER Corp. All rights reserved. Except in the case of
 * internal use for NAVER, unauthorized use of redistribution of this software
 * are strongly prohibited.
 */

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

// library mapping to use forEach
HTMLCollection.prototype.forEach = Array.prototype.forEach;
NodeList.prototype.forEach = Array.prototype.forEach;

window.addEventListener("DOMContentLoaded", function() {
	myReservation.init();
});

var myReservation = {
	domElements : {
		totalFigure : document.querySelector("#_total_figure"),
		yetFigure : document.querySelector("#_yet_figure"),
		usedFigure : document.querySelector("#_used_figure"),
		cancelFigure : document.querySelector("#_cancel_figure"),
		
		sectionProcessing : document.querySelector(".card._processing"),
		sectionConfirmed : document.querySelector(".card.confirmed"),
		sectionUsed : document.querySelector(".card.used"),
		sectionCanceled : document.querySelector(".card.used.cancel"),
		
		cancelPopup : document.querySelector(".popup_booking_wrapper")
	},

	constants : {
	},

	urls : {
		RESERVATION : "/reservation/api/reservations/",
	},

	templates : {
		reservationItem : document.querySelector("#rsvCardItem").innerHTML
	},

	state : {
		reservation_data : "",
	},

	parser : new DOMParser(),

	init : function() {
		domElements = this.domElements;
		urls = this.urls;
		constants = this.constants;
		state = this.state;
		templates = this.templates;
		parser = this.parser;
		
		fetchData = this.fetchData;
		renderReservations = this.renderReservations;
		processCancellation = this.processCancellation;
		
		fetchData();
	},
	
	fetchData : function() {
		xhrGetRequest(urls.RESERVATION, (respText) => {
			state.reservation_data = JSON.parse(respText);
			renderReservations();
		});
	},
	
	renderReservations : function() {
		arrayToElementRenderer(state.reservation_data.reservations,domElements.sectionConfirmed,templates.reservationItem);
		let reservations = domElements.sectionConfirmed.querySelectorAll("article");
		
		reservations.forEach((item) => {
			new ReservationCard(item, state.reservation_data.reservations,domElements.cancelPopup);
		});
	}
}
