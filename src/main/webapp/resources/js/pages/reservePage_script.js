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
	reservePage.init();
});

const TICKET_TYPES = {
	A : "성인",
	Y : "청소년",
	B : "유아",
	S : "셋트",
	D : "장애인",
	C : "지역주민",
	E : "얼리버드",
};

const HALL_TYPES = {
	V : "VIP",
	R : "R석",
	B : "B석",
	S : "S석",
	D : "평일",
}

var reservePage = {
	domElements : {

	},

	constants : {
		DISPLAY_INFO_ID : "",
	},

	urls : {
		DETAIL : "/reservation/api/products/",
	},

	state : {
		detail_data : "",
	},

	parser : new DOMParser(),

	init : function() {
		domElements = this.domElements;
		urls = this.urls;
		constants = this.constants;
		state = this.state;
		parser = this.parser;
		
		fetchDetailData = this.fetchDetailData;
		
		var url_string = window.location.href;
	    var url = new URL(url_string);
		constants.DISPLAY_INFO_ID = url.searchParams.get("id");
		fetchDetailData();
	},
	
	fetchDetailData : function(){
	    xhrGetRequest(urls.DETAIL + constants.DISPLAY_INFO_ID,(respText) => {
	    	state.detail_data = JSON.parse(respText);
	    	console.log(state.detail_data);
	    	document.querySelector(".preview_txt_tit").innerHTML= state.detail_data.displayInfo.productDescription;
	    	document.querySelector(".title").innerHTML = state.detail_data.displayInfo.productDescription;
	    	
	    	document.querySelector(".img_thumb").src = state.detail_data.productImages[0].saveFileName
	    	let details = document.querySelectorAll(".store_details p");
	    	details[0].innerHTML = "장소  : " + state.detail_data.displayInfo.placeName + "<br>"+
	    							"기간 : 2017.2.17.(금)~2017.4.18.(화)";
	    	details[1].innerHTML = state.detail_data.displayInfo.openingHours;
	    });
	},


}
