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
		countControlContainer : document.querySelector(".ticket_body"),
		totalCountBottom : document.querySelector("#totalCount"),
		bookButtonWrapper : document.querySelector(".bk_btn_wrap"),
		agreementButton : document.querySelector(".chk_agree"),
		reservationForm : document.querySelector(".form_horizontal")
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
		updateTotalCountBottom = this.updateTotalCountBottom;
		inputValidationAttachment = this.inputValidationAttachment;
		agreementShowBtnInit = this.agreementShowBtnInit;
		inputValidationErrorMsg = this.inputValidationErrorMsg;
		
		constants.DISPLAY_INFO_ID = new URL(window.location.href).searchParams.get("id");
		fetchDetailData();
	},
	
	fetchDetailData : function(){
	    xhrGetRequest(urls.DETAIL + constants.DISPLAY_INFO_ID,(respText) => {
	    	state.detail_data = JSON.parse(respText);
	    	renderData();
	    });
	},
	
	renderData : function() {
		document.querySelector(".preview_txt_tit").innerHTML= state.detail_data.displayInfo.productDescription;
    	document.querySelector(".title").innerHTML = state.detail_data.displayInfo.productDescription;
    	document.querySelector(".img_thumb").src = state.detail_data.productImages[0].saveFileName;
    	
    	let details = document.querySelectorAll(".store_details p");
    	details[0].innerHTML = "장소  : " + state.detail_data.displayInfo.placeName + "<br>";
    	
    	details[1].innerHTML = state.detail_data.displayInfo.openingHours;
    	
    	details[2].innerHTML = priceInterpreter();
    	
    	// add priceController with interpreted data
    	arrayToElementRenderer(state.detail_data.productPrices,domElements.countControlContainer,templates.countControlItem);
    	priceControllerInit();
    	updateTotalCountBottom();
    	inputValidationAttachment();
    	agreementShowBtnInit();
	},
	
	priceControllerInit : function() {
		let controllers = domElements.countControlContainer.querySelectorAll(".qty");
		controllers.forEach((item) => {
			let addButton = item.querySelector("a[title='더하기']");
			let reduceButton = item.querySelector("a[title='빼기']");
			
			addButton.addEventListener("click", (e) => {
				let wrapper = addButton.closest(".qty");
				let id = addButton.closest(".qty").dataset.id;
				let product = state.prices[id]; 
				product.qty++;
				
				let qtyArea = wrapper.querySelector(".count_control_input");
				qtyArea.value = product.qty;
				if(qtyArea.classList.contains("disabled")){
					qtyArea.classList.remove("disabled");
				}
				if(reduceButton.classList.contains("disabled")){
					reduceButton.classList.remove("disabled");
				}
				
				let totalPriceArea = wrapper.querySelector(".total_price");
				totalPriceArea.innerHTML = product.price * product.qty;
				totalPriceArea.parentElement.style.color = "black";
				
				updateTotalCountBottom();
			});
			
			reduceButton.addEventListener("click", (e) => {
				let wrapper = addButton.closest(".qty");
				let id = addButton.closest(".qty").dataset.id;
				let product = state.prices[id]; 
				
				if(product.qty == 0){
					return;
				}
				product.qty--;
				
				let qtyArea = wrapper.querySelector(".count_control_input");
				qtyArea.value = product.qty;
				if(product.qty == 0){
					if(!qtyArea.classList.contains("disabled")){
						qtyArea.classList.add("disabled");
					}
					if(!reduceButton.classList.contains("disabled")){
						reduceButton.classList.add("disabled");
					}
				}

				let totalPriceArea = wrapper.querySelector(".total_price");
				totalPriceArea.innerHTML = product.price * product.qty;
				if(product.qty == 0){
					totalPriceArea.parentElement.style.color = "";
				}
				
				updateTotalCountBottom();
			});
		});
	},
	
	updateTotalCountBottom : function() {
		let totalCount = 0;
		for (var key in state.prices){
		    var value = state.prices[key];
		    totalCount += value.qty;
		}
		domElements.totalCountBottom.innerHTML = totalCount;
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
    		state.prices[item.productPriceId] = {price : item.price, qty :0};
    		let currentType = state.pricingType[item.priceTypeName];
    		let currentString = "";
    		for (var key in currentType) {
    		    if (currentType.hasOwnProperty(key)) {
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
	
	agreementShowBtnInit : function(){
		document.querySelectorAll(".btn_agreement").forEach((item) => {
			item.addEventListener("click", (e) => {
				let container = item.parentElement;
				if(container.classList.contains("open")){
					container.classList.remove("open");
					item.querySelector(".btn_text").innerText ="보기";
					item.querySelector(".fn").classList.replace("fn-up2", "fn-down2");
				} else {
					container.classList.add("open");
					item.querySelector(".btn_text").innerText = "접기";
					item.querySelector(".fn").classList.replace("fn-down2", "fn-up2");
				}
			});
		});
	},
	
	inputValidationErrorMsg : function(criteria) {
		criteria.nextElementSibling.style.visibility = "visible";
		setTimeout(()=>{
			criteria.nextElementSibling.style.visibility = "hidden";
		},2000);
	},
	
	inputValidationAttachment : function() { 
		let name = domElements.reservationForm.querySelector("#name");
		let tel = domElements.reservationForm.querySelector("#tel");
		let email = domElements.reservationForm.querySelector("#email");
		
		domElements.bookButtonWrapper.querySelector("button").addEventListener("click",(e) => {
			e.preventDefault();
			
			let nameValid = (/[가-힣a-zA-Z]+$/).test(name.value);
			let emailValid = (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/).test(email.value);
			let telValid = (/^[\+]?[(]?[0-9]{2,3}[)]?[-\s\.]?[0-9]{3,4}[-\s\.]?[0-9]{4}$/im).test(tel.value); 
				
			if(!nameValid) {
				inputValidationErrorMsg(name);
			} else if(!telValid){
				inputValidationErrorMsg(tel);
			} else if(!emailValid) {
				inputValidationErrorMsg(email);
			} else {
				domElements.reservationForm.submit();
			}
		});
		
		domElements.agreementButton.addEventListener("click", (e) => { 
			let bookButton = domElements.bookButtonWrapper;

			if(domElements.agreementButton.checked){
				if(bookButton.classList.contains("disable")){
					bookButton.classList.remove("disable");
				} 
			} else if(!bookButton.classList.contains("disable")){
				bookButton.classList.add("disable");
			}
		});
	},
}
