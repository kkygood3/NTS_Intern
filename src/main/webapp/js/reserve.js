document.addEventListener("DOMContentLoaded", function() {
	getBookingPage();
});

function setReservationInfo(){
	var domElements = new DomElements();
	
	var reservationPrices = []
	domElements.container.priceContainer.querySelectorAll(".count_control").forEach(function(elements){
		var price = {
				count : elements.querySelector(".count_control_input").value,
				productPriceId : elements.querySelector(".count_control_input").dataset.productPriceId,
		}
		if(price.count !== "0"){
			reservationPrices.push(price)
		}
	});
	
	var reservationInfo = {
		displayInfoId: domElements.displayInfoId,
		prices: reservationPrices,
		productId: domElements.getElements.title.dataset.productId,
		reservationEmail: domElements.getElements.bkEmail.value,
		reservationName: domElements.getElements.bkName.value,
		reservationTel: domElements.getElements.bkTel.value,
		reservationDate: domElements.getElements.bkDate.innerHTML,
	}
	
	var httpRequest;
	
	if (window.XMLHttpRequest) {
		httpRequest =  new XMLHttpRequest();
		
		httpRequest.onreadystatechange = function() {
			if (httpRequest.readyState === 4 && httpRequest.status === 200) {
				window.location.href = "../.."
			}
		}
		
		httpRequest.open("POST", "../../api/reservations");
		httpRequest.setRequestHeader("Content-type", "application/json");
		httpRequest.send(JSON.stringify(reservationInfo));
	}
}

function getBookingPage(){
	var domElements = new DomElements();
	var template = new Templating();
	var setEvent = new SetEvent();
	var setAmountOfPayment = new SetAmountOfPayment();
	var display = new Display();
	
	var httpRequest;
	
	template.compareDiscountRateToZero();
	template.convertTypeName();
	
	if (window.XMLHttpRequest) {
		httpRequest =  new XMLHttpRequest();
		
		httpRequest.onreadystatechange = function() {
			var jsonResponse;
			
			if (httpRequest.readyState === 4 && httpRequest.status === 200) {
				jsonResponse = JSON.parse(httpRequest.responseText);
				
				display.displayInfo(jsonResponse);
				display.ticketPrice(jsonResponse);
			}
		}
		
		httpRequest.open("GET", "../../api/products/" + domElements.displayInfoId);
		httpRequest.setRequestHeader("Content-type", "charset=utf-8");
		httpRequest.send();
	}
	
	setEvent.validateInputValue(domElements.getElements.bkName, /(^[가-힣]{2,}$|^[a-zA-Z]{3,}$)/);
	setEvent.formattingTelNumber();
	setEvent.validateInputValue(domElements.getElements.bkEmail, /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.(com|net|co\.kr)$/);
	
	setEvent.changeTicketCount();
	setEvent.openTerms();
	setEvent.acceptTerms();
	setEvent.goToPrevPage();
	setEvent.scrollTop();
	setEvent.makeReservation();
}


function DomElements(){}

DomElements.prototype.displayInfoId = window.location.href.match(/detail\/\d+/)[0].split("/")[1]

DomElements.prototype.getElements = {
	title : document.querySelector(".top_title").querySelector(".title"),
	mainImage : document.querySelector(".img_thumb"),
	
	ticketCounts : [],
	totalCount : document.querySelector(".inline_txt.selected"),
	
	btnBack : document.querySelector(".btn_back"),
	btnTop : document.querySelector(".lnk_top"),
	
	bkName : document.querySelector("#name"),
	bkTel : document.querySelector("#tel"),
	bkEmail : document.querySelector("#email"),
	bkDate : document.querySelector("#reservation_date"),
	
	btnShowMore : document.querySelectorAll(".btn_agreement"),
	btnAgree : document.querySelector(".chk_agree")
}

DomElements.prototype.container = {
	displayInfoContainer : document.querySelector(".store_details"),
	priceContainer : document.querySelector(".ticket_body"),
	bkBtnContainer : document.querySelector(".bk_btn_wrap") 
}

DomElements.prototype.template = {
	displayInfoTemplate : document.querySelector("#diplayInfoTemplate").innerHTML,
	priceTemplate : document.querySelector("#priceTemplate").innerHTML,
}


function Templating(){}

Templating.prototype.bindTemplate = function(template){
	return Handlebars.compile(template);
}

Templating.prototype.priceType = {
	A : "성인",
	Y : "청소년",
	B : "유아",
	D : "장애인",
	C : "지역주민",
	E : "어얼리버드",
	V : "VIP",
	R : "R석",
	S : "S석"
}

Templating.prototype.convertTypeName = function(){
	Handlebars.registerHelper('convertTypeName', function(typeName) {
		return this.priceType[typeName];
	}.bind(this));
}

Templating.prototype.compareDiscountRateToZero = function(){
	Handlebars.registerHelper('ifNotZero', function(value, options) {
		if(value !== 0) {
		    return options.fn(this);
		} else {
			return options.inverse(this);
		}
	});
}


function SetAmountOfPayment(){
	this.domElements = new DomElements();
}

SetAmountOfPayment.prototype.amountOfPayment = function(){
	var amountOfPayment = 0;
	
	document.querySelectorAll(".total_price").forEach(function(price){
		amountOfPayment += parseInt(price.innerHTML);
	});
	
	return amountOfPayment;
}

SetAmountOfPayment.prototype.checkTicketCount = function(){
	var totalCount = 0;
	this.domElements.getElements.ticketCounts.forEach(function(ticketCnt){
		if(ticketCnt.value === "0"){
			ticketCnt.previousElementSibling.classList.add("disabled");
			ticketCnt.classList.add("disabled");
		}
		else {
			ticketCnt.previousElementSibling.classList.remove("disabled");
			ticketCnt.classList.remove("disabled");
		}
		totalCount += parseInt(ticketCnt.value);
	});
	
	this.domElements.getElements.totalCount.value = totalCount;
	if(totalCount === 0) {
		this.domElements.getElements.totalCount.innerHTML = "선택된 티켓이 없습니다.";
	} else {
		this.domElements.getElements.totalCount.innerHTML = "총 " + totalCount + "매, " + this.amountOfPayment() + "원"
	}
}

SetAmountOfPayment.prototype.changeTicketCount = function(event){
	var ticketPrice;
	var ticketCnt;
	var totalPrice;
	
	event.preventDefault();
	
	if(event.target.classList.contains("btn_plus_minus")){
		ticketPrice = event.target.parentNode.parentNode.parentNode.querySelector(".price").innerHTML
		ticketCnt = event.target.parentNode.querySelector(".count_control_input")
		totalPrice = event.target.parentNode.parentNode.querySelector(".total_price");
		
		if(event.target.classList.contains("ico_plus3")) {
			ticketCnt.value++;
		} else if(!event.target.classList.contains("disabled") && event.target.classList.contains("ico_minus3")) {
			ticketCnt.value--;
		}
		
		totalPrice.innerHTML = ticketPrice * ticketCnt.value;
		this.checkTicketCount();
	}
}


function SetEvent(){
	this.domElements = new DomElements();
	this.setAmountOfPayment = new SetAmountOfPayment();
}

SetEvent.prototype.changeTicketCount = function(){
	this.domElements.container.priceContainer.addEventListener("click", function(event){
		if(!event.target.classList.contains("disabled")){
			this.domElements.getElements.btnAgree.checked = false;
			this.domElements.container.bkBtnContainer.classList.add("disable");
		};
		
		this.setAmountOfPayment.changeTicketCount(event);
	}.bind(this));
}

SetEvent.prototype.formattingTelNumber = function(){
	this.domElements.getElements.bkTel.addEventListener("input", function(event){
		event.target.value = event.target.value.replace(/[^0-9]/g, "");

		if(event.target.value.length > 10){
			event.target.value = event.target.value.substring(0,3) + "-" + event.target.value.substring(3,7) + "-" + event.target.value.substring(7);
		} else if(event.target.value.length > 9) {
			event.target.value = event.target.value.substring(0,3) + "-" + event.target.value.substring(3,6) + "-" + event.target.value.substring(6);
		} else if(event.target.value.length === 8) {
			event.target.value = event.target.value.substring(0,4) + "-" + event.target.value.substring(4);
		} else if(event.target.value.length > 6) {
			event.target.value = event.target.value.substring(0,2) + "-" + event.target.value.substring(2,5) + "-" + event.target.value.substring(5);
		} else if(event.target.value.length > 2) {
			event.target.value = event.target.value.substring(0,2) + "-" + event.target.value.substring(2);
		} else {
			event.target.value = event.target.value;
		}
		
		this.validateInputValue(event.target, /^([0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}|[0-9]{4}-[0-9]{4})$/);
	}.bind(this));
}

SetEvent.prototype.validateInputValue = function(inputTag, regularExpression){
	inputTag.addEventListener("input", function(event){
		this.domElements.getElements.btnAgree.checked = false;
		this.domElements.container.bkBtnContainer.classList.add("disable");
		
		if(regularExpression.test(event.target.value)){
			event.target.classList.add("valid_value");
			event.target.classList.remove("wrong_value");
			event.target.parentNode.querySelector(".warning_msg").style.display = "none";
		} else {
			event.target.classList.add("wrong_value");
			event.target.classList.remove("valid_value");
			event.target.parentNode.querySelector(".warning_msg").style.display = "inline";
		}

		if(event.target.value.length === 0){
			event.target.parentNode.querySelector(".warning_msg").style.display = "none";
		}
	}.bind(this));
}

SetEvent.prototype.openTerms = function(){
	this.domElements.getElements.btnShowMore.forEach(function(btnOpen){
		btnOpen.addEventListener("click", function(){
			event.preventDefault();
			
			if(this.parentNode.classList.contains("open")){
				this.parentNode.classList.remove("open");
				this.querySelector(".btn_text").innerHTML = "보기";
				this.querySelector(".fn").classList.add("fn-down2");
				this.querySelector(".fn").classList.remove("fn-up2");
			} else {
				this.parentNode.classList.add("open");
				this.querySelector(".btn_text").innerHTML = "닫기";
				this.querySelector(".fn").classList.add("fn-up2");
				this.querySelector(".fn").classList.remove("fn-down2");
			}
		});
	});
}

SetEvent.prototype.acceptTerms = function(){
	var bkName = this.domElements.getElements.bkName;
	var bkTel = this.domElements.getElements.bkTel;
	var bkEmail = this.domElements.getElements.bkEmail;
	var ticketCnt = this.domElements.getElements.totalCount;
	var lastMsg = this.domElements.container.bkBtnContainer.parentNode.querySelector(".warning_msg");
	
	this.domElements.getElements.btnAgree.addEventListener("change", function(){
		if(bkName.classList.contains("valid_value")
			&& bkTel.classList.contains("valid_value")
			&& bkEmail.classList.contains("valid_value")
			&& ticketCnt.value !== 0){

			lastMsg.style.display = "none";
			
			if(this.domElements.container.bkBtnContainer.classList.contains("disable")){
				this.domElements.container.bkBtnContainer.classList.remove("disable");
			} else {
				this.domElements.container.bkBtnContainer.classList.add("disable");
			}
		} else {
			lastMsg.style.display = "block";
			this.domElements.getElements.btnAgree.checked = false;
			this.domElements.container.bkBtnContainer.classList.add("disable");
		}
	}.bind(this));
}

SetEvent.prototype.goToPrevPage = function(){
	this.domElements.getElements.btnBack.addEventListener("click", function(){
		event.preventDefault();
		history.back();
	});
}

SetEvent.prototype.scrollTop = function(){
	this.domElements.getElements.btnTop.addEventListener("click", function(){
		event.preventDefault();
		document.documentElement.scrollTop = 0;
	});
}

SetEvent.prototype.makeReservation = function(){
	this.domElements.container.bkBtnContainer.addEventListener("click", function(){
		if(!this.classList.contains("disable")){
			setReservationInfo();
		}
	});
}


function Display(){
	this.domElements = new DomElements();
	this.template = new Templating();
	this.setAmountOfPayment = new SetAmountOfPayment();
}

Display.prototype.displayInfo = function(jsonResponse){
	var bindDisplayInfo = this.template.bindTemplate(this.domElements.template.displayInfoTemplate);
	
	this.domElements.getElements.title.innerHTML = jsonResponse["displayInfo"].productDescription;
	this.domElements.getElements.title.dataset.productId = jsonResponse["displayInfo"].productId;
	this.domElements.getElements.mainImage.src = "../../" + jsonResponse["productImages"][0].saveFileName;
	this.domElements.container.displayInfoContainer.innerHTML = bindDisplayInfo(jsonResponse);
}

Display.prototype.ticketPrice = function(jsonResponse){
	var bindTicketPrice = this.template.bindTemplate(this.domElements.template.priceTemplate);
	
	this.domElements.container.priceContainer.innerHTML = bindTicketPrice(jsonResponse);
	
	this.domElements.getElements.ticketCounts = document.querySelectorAll(".count_control_input");
	this.setAmountOfPayment.checkTicketCount();
}