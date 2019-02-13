/**
 * Copyright 2019 NAVER Corp. All rights reserved. Except in the case of
 * internal use for NAVER, unauthorized use of redistribution of this software
 * are strongly prohibited.
 */

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */


function priceToStrInterpreter() {
	// checkType
	state.detail_data.productPrices.forEach((item) => {
		if(item.priceTypeName === "V" || item.priceTypeName === "R"){
			state.pricingType = HALL_TYPES;
		}
	});    	
	/*
	 * accumulate strings and render information, init control data by pushing
	 * JSON object to prices array
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
}

function SubmitButton(item) {
	item.addEventListener("click",(e) => {
		e.preventDefault();
		
		let nameValid = (/[가-힣a-zA-Z]+$/).test(name.value);
		let emailValid = (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/).test(email.value);
		let telValid = (/^[\+]?[(]?[0-9]{2,3}[)]?[-\s\.]?[0-9]{3,4}[-\s\.]?[0-9]{4}$/im).test(tel.value); 
			
		if(!nameValid) {
			inputValidationErrorMsg(name);
			return;
		} else if(!telValid){
			inputValidationErrorMsg(tel);
			return;
		} else if(!emailValid) {
			inputValidationErrorMsg(email);
			return;
		} 
		
		domElements.reservationForm.submit();
	});
}

function AgreementButton(item) {
	item.addEventListener("click", (e) => { 
		let bookButton = domElements.bookButtonWrapper;

		if(domElements.agreementButton.checked){
			if(bookButton.classList.contains("disable")){
				bookButton.classList.remove("disable");
			} 
		} else {
			bookButton.classList.add("disable");
		}
	});
}

function EulaButton (item) {
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
}


function CountController (item) {
	this.addButton = item.querySelector("a[title='더하기']");
	this.reduceButton = item.querySelector("a[title='빼기']");

	this.totalCountBottom = document.querySelector("#totalCount");

	this.addButton.addEventListener("click", (e) => {
		this.increment();
		this.updateTotalCountBottom();
	});
	
	this.reduceButton.addEventListener("click", (e) => {
		this.decrement();
		this.updateTotalCountBottom();
	});
}

CountController.prototype.increment = function(){
	let wrapper = this.addButton.closest(".qty");
	let id = this.addButton.closest(".qty").dataset.id;
	let product = state.prices[id]; 
	product.qty++;

	let qtyArea = wrapper.querySelector(".count_control_input");
	qtyArea.value = product.qty;
	if(qtyArea.classList.contains("disabled")){
		qtyArea.classList.remove("disabled");
	}
	if(this.reduceButton.classList.contains("disabled")){
		this.reduceButton.classList.remove("disabled");
	}
	
	let totalPriceArea = wrapper.querySelector(".total_price");
	totalPriceArea.innerHTML = product.price * product.qty;
	totalPriceArea.parentElement.style.color = "black";
}

CountController.prototype.decrement= function() {
	let wrapper = this.addButton.closest(".qty");
	let id = this.addButton.closest(".qty").dataset.id;
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
		if(!this.reduceButton.classList.contains("disabled")){
			this.reduceButton.classList.add("disabled");
		}
	}

	let totalPriceArea = wrapper.querySelector(".total_price");
	totalPriceArea.innerHTML = product.price * product.qty;
	if(product.qty == 0){
		totalPriceArea.parentElement.style.color = "";
	}
}
CountController.prototype.updateTotalCountBottom = function() {
	let totalCount = 0;
	for (var key in state.prices){
	    var value = state.prices[key];
	    totalCount += value.qty;
	}
	this.totalCountBottom.innerHTML = totalCount;
}
