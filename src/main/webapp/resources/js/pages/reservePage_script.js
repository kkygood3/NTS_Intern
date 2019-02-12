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
	A : {
		name : "성인",
		requirement : "(만 19~64세)"
	},
	Y : {
		name : "청소년",
		requirement : "(만 13~18세)"
	},
	B : {
		name : "유아",
		requirement : "(만 4~12세)"
	},
	S : {
		name : "셋트",
		requirement : "(만 19~64세)"
	},
	D : {
		name : "장애인",
	},
	C : {
		name : "지역주민",
	},
	E : {
		name : "얼리버드",
	},
};

const HALL_TYPES = {
	V : {
		name : "VIP",
	},
	R : {
		name : "R석",
	},
	S : {
		name : "S석",
	},
	A :{
		name : "A석",
	}, 
	B :{
		name : "B석",
	}, 
	D :{
		name :  "평일",
	},
};

var reservePage = {
	domElements : {
		countControlContainer : document.querySelector(".ticket_body")
	},

	constants : {
		DISPLAY_INFO_ID : "",
	},

	urls : {
		DETAIL : "/reservation/api/products/",
	},
	
	templates : {
		countControlItem : document.querySelector("#countControlItem").innerHTML
	},
	
	state : {
		detail_data : "",
		pricingType : TICKET_TYPES,
		prices : {}
	},

	parser : new DOMParser(),

	init : function() {
		domElements = this.domElements;
		urls = this.urls;
		constants = this.constants;
		state = this.state;
		templates = this.templates;
		parser = this.parser;
		
		fetchDetailData = this.fetchDetailData;
		renderData = this.renderData;
		priceInterpreter = this.priceInterpreter;
		priceControllerInit = this.priceControllerInit;
		
		constants.DISPLAY_INFO_ID = new URL(window.location.href).searchParams.get("id");
		fetchDetailData();
	},
	
	fetchDetailData : function(){
	    xhrGetRequest(urls.DETAIL + constants.DISPLAY_INFO_ID,(respText) => {
	    	state.detail_data = JSON.parse(respText);
	    	console.log(state.detail_data);
	    	renderData();
	    });
	},
	
	renderData : function() {
		document.querySelector(".preview_txt_tit").innerHTML= state.detail_data.displayInfo.productDescription;
    	document.querySelector(".title").innerHTML = state.detail_data.displayInfo.productDescription;
    	document.querySelector(".img_thumb").src = state.detail_data.productImages[0].saveFileName;
    	
    	let details = document.querySelectorAll(".store_details p");
    	details[0].innerHTML = "장소  : " + state.detail_data.displayInfo.placeName + "<br>"+
    							"기간 : 2017.2.17.(금)~2017.4.18.(화)";
    	details[1].innerHTML = state.detail_data.displayInfo.openingHours;
    	
    	details[2].innerHTML = priceInterpreter();
    	
    	// add priceController with interpreted data
    	arrayToElementRenderer(state.detail_data.productPrices,domElements.countControlContainer,templates.countControlItem);
    	priceControllerInit();
	},
	
	priceControllerInit : function() {
		domElements.countControlContainer.querySelectorAll(".qty")
	},
	
	priceInterpreter : function() {
		// checkType
    	state.detail_data.productPrices.forEach((item) => {
    		if(item.priceTypeName === "V" || item.priceTypeName === "R"){
    			state.pricingType = HALL_TYPES;
    		}
    	});    	
    	/*
		 * accumulate strings and render information, init control data by
		 * pushing JSON object to prices array
		 */
    	let priceString = "";
    	state.detail_data.productPrices.forEach((item) => {
    		state.prices[item.productPriceId] = {price : item.price, total: 0, qty :0};
    		let currentType = state.pricingType[item.priceTypeName];
    		let currentString = "";
    		for (var key in currentType) {
    		    if (currentType.hasOwnProperty(key)) {
    		        console.log(key + " -> " + currentType[key]);
    		        currentString += currentType[key];
    		        if(key === "name") {
    		        	item.name = currentType[key];
    		        }
    		    }
    		}
    		currentString += " " + item.price + "원 할인율 " + item.discountRate + "%<br>";
    		priceString += currentString;
    	});
    	return priceString;
	},
	
	
}
