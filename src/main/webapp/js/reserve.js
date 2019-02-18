document.addEventListener("DOMContentLoaded", function() {
	getReservePage();
});

function getReservePage(){
	var domElements = new DomElements();
	var template = new Template();
	var eventAdder = new EventAdder();
	var inputTagValidator = new InputTagValidator();
	var paymentInfo = new PaymentInfo();
	var displayHelper = new DisplayHelper();
	
	var callBack = function(data){
		displayHelper.displayExhibitionInfo(data);
		displayHelper.displayTicketPrice(data);
	}
	
	template.compareDiscountRateToZero();
	template.convertTypeName();
	
	ajaxSend("GET", "../../api/products/" + displayHelper.displayInfoId, callBack, "charset=utf-8");
	
	inputTagValidator.validateInputTag(domElements.elementList.bkName, /(^[가-힣]{2,}$|^[a-zA-Z]{3,}$)/);
	inputTagValidator.validateInputTag(domElements.elementList.bkTel, /^([0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}|[0-9]{4}-[0-9]{4})$/);
	inputTagValidator.validateInputTag(domElements.elementList.bkEmail, /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.(com|net|co\.kr)$/);
	
	eventAdder.addEventToTicketInfoContainer();
	eventAdder.addEventToBtnShowDetailTerms();
	eventAdder.addEventToBtnAgree();
	eventAdder.addEventToBtnBack();
	eventAdder.addEventToBtnScrollTop();
	eventAdder.addEventToBtnReserve();
}

function DomElements(){}
DomElements.prototype.elementList = {
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
	
	btnShowDtailTerms : document.querySelectorAll(".btn_agreement"),
	btnAgree : document.querySelector(".chk_agree"),

	bkBtn : document.querySelector(".bk_btn_wrap")
}


function Template(){}
Template.prototype = {
	displayInfoTemplate : document.querySelector("#diplayInfoTemplate").innerHTML,
	displayInfoContainer : document.querySelector(".store_details"),
	ticketInfoTemplate : document.querySelector("#ticketInfoTemplate").innerHTML,
	ticketInfoContainer : document.querySelector(".ticket_body"),
	
	priceType : {
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
}

Template.prototype.bindTemplate = function(template){
	return Handlebars.compile(template);
}

Template.prototype.convertTypeName = function(){
	Handlebars.registerHelper('convertTypeName', function(typeName) {
		return this.priceType[typeName];
	}.bind(this));
}

Template.prototype.compareDiscountRateToZero = function(){
	Handlebars.registerHelper('ifNotZero', function(value, options) {
		if(value !== 0) {
		    return options.fn(this);
		} else {
			return options.inverse(this);
		}
	});
}


function PaymentInfo(){
	this.domElements = new DomElements();
}

PaymentInfo.prototype.setAmountOfPayment = function(){
	var amountOfPayment = 0;
	
	document.querySelectorAll(".total_price").forEach(function(price){
		amountOfPayment += parseInt(price.innerHTML);
	});
	
	return amountOfPayment;
}

PaymentInfo.prototype.displaySelectedTicketInfo = function(){
	var totalCount = 0;
	this.domElements.elementList.ticketCounts.forEach(function(ticketCnt){
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
	
	this.domElements.elementList.totalCount.value = totalCount;
	if(totalCount === 0) {
		this.domElements.elementList.totalCount.innerHTML = "선택된 티켓이 없습니다.";
	} else {
		this.domElements.elementList.totalCount.innerHTML = "총 " + totalCount + "매, " + this.setAmountOfPayment() + "원"
	}
}

PaymentInfo.prototype.setTotalPrice = function(classListOfBtn){
	var ticketPrice = event.target.parentNode.parentNode.parentNode.querySelector(".price").innerHTML;
	var ticketCnt = event.target.parentNode.querySelector(".count_control_input");
	var totalPrice = event.target.parentNode.parentNode.querySelector(".total_price");
	
	if(classListOfBtn.contains("ico_plus3")) {
		ticketCnt.value++;
	} else if(!classListOfBtn.contains("disabled") && classListOfBtn.contains("ico_minus3")) {
		ticketCnt.value--;
	}
	
	totalPrice.innerHTML = ticketPrice * ticketCnt.value;
	this.displaySelectedTicketInfo();
}


function EventAdder(){
	this.domElements = new DomElements();
	this.template = new Template();
	this.displayHelper = new DisplayHelper();
	this.paymentInfo = new PaymentInfo();
}

EventAdder.prototype.addEventToTicketInfoContainer = function(){
	this.template.ticketInfoContainer.addEventListener("click", function(event){
		event.preventDefault();
		
		if(!event.target.classList.contains("disabled")){
			this.domElements.elementList.btnAgree.checked = false;
			this.domElements.elementList.bkBtn.classList.add("disable");
		};
		
		if(event.target.classList.contains("btn_plus_minus")){
			this.paymentInfo.setTotalPrice(event.target.classList);
		}
	}.bind(this));
}

EventAdder.prototype.addEventToBtnShowDetailTerms = function(){
	this.domElements.elementList.btnShowDtailTerms.forEach(function(btnOpen){
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

EventAdder.prototype.addEventToBtnAgree = function(){
	var bkName = this.domElements.elementList.bkName;
	var bkTel = this.domElements.elementList.bkTel;
	var bkEmail = this.domElements.elementList.bkEmail;
	var ticketCnt = this.domElements.elementList.totalCount;
	var lastMsg = this.domElements.elementList.bkBtn.parentNode.querySelector(".warning_msg");
	
	this.domElements.elementList.btnAgree.addEventListener("change", function(){
		if(bkName.classList.contains("valid_value")
			&& bkTel.classList.contains("valid_value")
			&& bkEmail.classList.contains("valid_value")
			&& ticketCnt.value !== 0){

			lastMsg.style.display = "none";
			
			if(this.domElements.elementList.bkBtn.classList.contains("disable")){
				this.domElements.elementList.bkBtn.classList.remove("disable");
			} else {
				this.domElements.elementList.bkBtn.classList.add("disable");
			}
		} else {
			lastMsg.style.display = "block";
			this.domElements.elementList.btnAgree.checked = false;
			this.domElements.elementList.bkBtn.classList.add("disable");
		}
	}.bind(this));
}

EventAdder.prototype.addEventToBtnBack = function(){
	this.domElements.elementList.btnBack.addEventListener("click", function(){
		event.preventDefault();
		history.back();
	});
}

EventAdder.prototype.addEventToBtnScrollTop = function(){
	this.domElements.elementList.btnTop.addEventListener("click", function(){
		event.preventDefault();
		document.documentElement.scrollTop = 0;
	});
}

EventAdder.prototype.addEventToBtnReserve = function(){
	this.domElements.elementList.bkBtn.addEventListener("click", function(event){
		var reservationInfo = new ReservationInfo(this.displayHelper.displayInfoId);
		var callBack = function(){
			window.location = "../..";
		}
		
		if(!event.target.classList.contains("disable")){
			ajaxSend("POST", "../../api/reservations", callBack, "application/json", JSON.stringify(reservationInfo.reservationData));
		}
	}.bind(this));
}


function DisplayHelper(){
	this.domElements = new DomElements();
	this.template = new Template();
	this.paymentInfo = new PaymentInfo();
}

DisplayHelper.prototype.displayInfoId = window.location.href.match(/detail\/\d+/)[0].split("/")[1];

DisplayHelper.prototype.displayExhibitionInfo = function(jsonResponse){
	var bindDisplayInfo = this.template.bindTemplate(this.template.displayInfoTemplate);
	
	this.domElements.elementList.title.innerHTML = jsonResponse["displayInfo"].productDescription;
	this.domElements.elementList.title.dataset.productId = jsonResponse["displayInfo"].productId;
	this.domElements.elementList.mainImage.src = "../../" + jsonResponse["productImages"][0].saveFileName;
	this.template.displayInfoContainer.innerHTML = bindDisplayInfo(jsonResponse);
}

DisplayHelper.prototype.displayTicketPrice = function(jsonResponse){
	var bindTicketInfo = this.template.bindTemplate(this.template.ticketInfoTemplate);
	
	this.template.ticketInfoContainer.innerHTML = bindTicketInfo(jsonResponse);
	
	this.domElements.elementList.ticketCounts = document.querySelectorAll(".count_control_input");
	this.paymentInfo.displaySelectedTicketInfo();
}


function ReservationInfo(displayInfoId){
	var domElements = new DomElements();
	var template = new Template();
	
	this.displayInfoId = displayInfoId;
	this.reservationPrices = []
	template.ticketInfoContainer.querySelectorAll(".count_control").forEach(function(elements){
		var price = {
				count : elements.querySelector(".count_control_input").value,
				productPriceId : elements.querySelector(".count_control_input").dataset.productPriceId,
		}
		if(price.count !== "0"){
			this.reservationPrices.push(price)
		}
	}.bind(this));
	
	this.reservationData = {
		displayInfoId: this.displayInfoId,
		prices: this.reservationPrices,
		productId: domElements.elementList.title.dataset.productId,
		reservationEmail: domElements.elementList.bkEmail.value,
		reservationName: domElements.elementList.bkName.value,
		reservationTel: domElements.elementList.bkTel.value,
		reservationDate: domElements.elementList.bkDate.innerHTML,
	}
}


function InputTagValidator(){
	this.domElements = new DomElements();
}

InputTagValidator.prototype.validateInputTag = function(inputTag, regularExpression){
	inputTag.addEventListener("input", function(event){
		this.domElements.elementList.btnAgree.checked = false;
		this.domElements.elementList.bkBtn.classList.add("disable");
		
		if(event.target.id === "tel") {
			event.target.value = this.setTelNumberformat(event.target.value);
		}
		
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

InputTagValidator.prototype.setTelNumberformat = function(telNumber){
	var formattedTelNumber;
	telNumber = telNumber.replace(/[^0-9]/g, "");
	
	if(telNumber.length > 10){
		formattedTelNumber = telNumber.substring(0,3) + "-" + telNumber.substring(3,7) + "-" + telNumber.substring(7);
	} else if(telNumber.length > 9) {
		formattedTelNumber = telNumber.substring(0,3) + "-" + telNumber.substring(3,6) + "-" + telNumber.substring(6);
	} else if(telNumber.length === 8) {
		formattedTelNumber = telNumber.substring(0,4) + "-" + telNumber.substring(4);
	} else if(telNumber.length > 6) {
		formattedTelNumber = telNumber.substring(0,2) + "-" + telNumber.substring(2,5) + "-" + telNumber.substring(5);
	} else if(telNumber.length > 2) {
		formattedTelNumber = telNumber.substring(0,2) + "-" + telNumber.substring(2);
	} else {
		formattedTelNumber = telNumber;
	}
	
	return formattedTelNumber;
}