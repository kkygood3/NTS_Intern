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
		totalfigure : document.querySelector("#total_figure"),
		yetfigure : document.querySelector("#yet_figure"),
		usedfigure : document.querySelector("#used_figure"),
		cancelfigure : document.querySelector("#cancel_figure"),
		
		sectionProcessing : document.querySelector(".card.processing"),
		sectionConfirmed : document.querySelector(".card.confirmed"),
		sectionUsed : document.querySelector(".card.used"),
		sectionCanceled : document.querySelector(".card.used.cancel"),
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
		
		fetchData();
	},
	
	fetchData : function() {
		xhrGetRequest(urls.RESERVATION, (respText) => {
			state.reservation_data = JSON.parse(respText);
			console.log(state.reservation_data);
			renderReservations();
		});
	},
	
	renderReservations : function() {
		console.log(state.reservation_data.reservations)
		arrayToElementRenderer(state.reservation_data.reservations,domElements.sectionConfirmed,templates.reservationItem);
	}

}
