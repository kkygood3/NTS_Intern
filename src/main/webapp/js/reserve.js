document.addEventListener("DOMContentLoaded", function() {
	reservePage.getReservPage(reservePage.displayInfoId);
});
var reservePage = {
	getReservPage: function(displayInfoId){
		this.compileHendlebars.compareDiscountRateToZero();
		this.compileHendlebars.convertTypeName();
		
		this.inputTagValidator.validateInputTag(this.elements.bkName, this.inputTagValidator.nameRegex);
		this.inputTagValidator.validateInputTag(this.elements.bkTel, this.inputTagValidator.telRegex);
		this.inputTagValidator.validateInputTag(this.elements.bkEmail, this.inputTagValidator.emailRegex);
		
		
		this.ajax.sendGet("/reservation/api/products/" + displayInfoId, this.ajaxOptions.getOptionsForDisplayContents());
		
		this.setEvent.setEventToTicketInfoContainer();
		this.setEvent.setEventToBtnShowDetailTerms();
		this.setEvent.setEventToBtnAgree();
		this.setEvent.setPrevPageLink();
		this.setEvent.scrollTop();
		this.setEvent.setEventToBtnReserve();
	},
	
	ajax : new AjaxSend(),
	
	ajaxOptions: {
		getOptionsForDisplayContents : function(){
			var options = {
				contentType : "charset=utf-8",
				callBack : this.reservePage.displayContents
			}
			
			return options;
		}.bind(this),
		
		getOptionsForMakeReservation : function(){
			var options = {
				contentType : "application/json",
				data : JSON.stringify(this.reservePage.reservationInfo.getReservationData()),
				callBack : function(){
					window.location = "/reservation";
				}
			}
			
			return options;
		}.bind(this)
	},
	
	displayContents: function(data){
		this.reservePage.displayExhibitionInfo(data);
		this.reservePage.displayTicketPrice(data);
	}.bind(this),
	
	displayInfoId : window.location.href.match(/detail\/\d+/)[0].split("/")[1],
	
	inputTagValidator: {
		nameRegex : /(^[가-힣]{2,}$|^[a-zA-Z]{3,}$)/,
		telRegex : /^([0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}|[0-9]{4}-[0-9]{4})$/,
		emailRegex : /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.(com|net|co\.kr)$/,
		
		validateInputTag : function(inputTag, regularExpression){
			inputTag.addEventListener("input", function(event){
				this.reservePage.elements.btnAgree.checked = false;
				this.reservePage.elements.bkBtn.classList.add("disable");
				
				if(event.target.id === "tel") {
					event.target.value = this.reservePage.inputTagValidator.setTelNumberformat(event.target.value);
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
		}.bind(this),
		
		setTelNumberformat : function(telNumber){
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
	},
	
	elements: {
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

		bkBtn : document.querySelector(".bk_btn_wrap"),
		lastMsg : document.querySelector(".bk_btn_wrap").parentNode.querySelector(".warning_msg")
	},
	
	container: {
		displayInfoContainer : document.querySelector(".store_details"),
		ticketInfoContainer : document.querySelector(".ticket_body"),
	},
	
	template: {
		displayInfoTemplate : document.querySelector("#diplayInfoTemplate").innerHTML,
		ticketInfoTemplate : document.querySelector("#ticketInfoTemplate").innerHTML,
	},
	
	compileHendlebars: {
		bindTemplate : function(template){
			return Handlebars.compile(template);
		},
		
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
		},
		
		convertTypeName : function(){
			Handlebars.registerHelper('convertTypeName', function(typeName) {
				return this.priceType[typeName];
			}.bind(this));
		},

 		compareDiscountRateToZero: function(){
			Handlebars.registerHelper('ifNotZero', function(value, options) {
				if(value !== 0) {
				    return options.fn(this);
				} else {
					return options.inverse(this);
				}
			});
		}
	},

	displayExhibitionInfo : function(jsonResponse){
		var bindDisplayInfo = this.compileHendlebars.bindTemplate(this.template.displayInfoTemplate);
		
		this.elements.title.innerHTML = jsonResponse["displayInfo"].productDescription;
		this.elements.title.dataset.productId = jsonResponse["displayInfo"].productId;
		this.elements.mainImage.src = "/reservation/" + jsonResponse["productImages"][0].saveFileName;
		this.container.displayInfoContainer.innerHTML = bindDisplayInfo(jsonResponse);
	},

	displayTicketPrice : function(jsonResponse){
		var bindTicketInfo = this.compileHendlebars.bindTemplate(this.template.ticketInfoTemplate);
		
		this.container.ticketInfoContainer.innerHTML = bindTicketInfo(jsonResponse);
		
		this.elements.ticketCounts = document.querySelectorAll(".count_control_input");
		this.paymentInfo.displaySelectedTicketInfo();
	},

	reservationInfo : {
		reservationPrices : [],
		
		setReservationPrices : function(){
			this.reservePage.container.ticketInfoContainer.querySelectorAll(".count_control").forEach(function(elements){
				var price = {
						count : elements.querySelector(".count_control_input").value,
						productPriceId : elements.querySelector(".count_control_input").dataset.productPriceId,
				}
				if(price.count !== "0"){
					this.reservePage.reservationInfo.reservationPrices.push(price)
				}
			}.bind(this));
		}.bind(this),
		
		getReservationData : function(){
			var reservationData;
			
			this.reservePage.reservationInfo.setReservationPrices();
			
			reservationData = {
					displayInfoId : this.reservePage.displayInfoId,
					prices : this.reservePage.reservationInfo.reservationPrices,
					productId : this.reservePage.elements.title.dataset.productId,
					reservationEmail : this.reservePage.elements.bkEmail.value,
					reservationName : this.reservePage.elements.bkName.value,
					reservationTel : this.reservePage.elements.bkTel.value,
					reservationDate : this.reservePage.elements.bkDate.innerHTML,
			}
			
			return reservationData;
		}.bind(this)
	},
	
	paymentInfo : {
		setAmountOfPayment : function(){
			var amountOfPayment = 0;
			
			document.querySelectorAll(".total_price").forEach(function(price){
				amountOfPayment += parseInt(price.innerHTML);
			});
			
			return amountOfPayment;
		},
		
		displaySelectedTicketInfo : function(){
			var totalCount = 0;
			this.reservePage.elements.ticketCounts.forEach(function(ticketCnt){
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
			
			this.reservePage.elements.totalCount.value = totalCount;
			if(totalCount === 0) {
				this.reservePage.elements.totalCount.innerHTML = "선택된 티켓이 없습니다.";
			} else {
				this.reservePage.elements.totalCount.innerHTML = "총 " + totalCount + "매, " + this.reservePage.paymentInfo.setAmountOfPayment() + "원"
			}
		}.bind(this),
		
		displayTotalPrice : function(classListOfBtn){
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
	},
	
	setEvent: {
		setEventToTicketInfoContainer : function(){
			this.reservePage.container.ticketInfoContainer.addEventListener("click", function(event){
				event.preventDefault();
				
				if(!event.target.classList.contains("disabled")){
					this.reservePage.elements.btnAgree.checked = false;
					this.reservePage.elements.bkBtn.classList.add("disable");
				};
				
				if(event.target.classList.contains("btn_plus_minus")){
					this.reservePage.paymentInfo.displayTotalPrice(event.target.classList);
				}
			}.bind(this));
		}.bind(this),

		setEventToBtnShowDetailTerms : function(){
			this.reservePage.elements.btnShowDtailTerms.forEach(function(btnOpen){
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
		}.bind(this),

		setEventToBtnAgree : function(){
			this.reservePage.elements.btnAgree.addEventListener("change", function(){
				if(this.reservePage.elements.bkName.classList.contains("valid_value")
					&& this.reservePage.elements.bkTel.classList.contains("valid_value")
					&& this.reservePage.elements.bkEmail.classList.contains("valid_value")
					&& this.reservePage.elements.totalCount.value !== 0){

					this.reservePage.elements.lastMsg.style.display = "none";
					
					if(this.reservePage.elements.bkBtn.classList.contains("disable")){
						this.reservePage.elements.bkBtn.classList.remove("disable");
					} else {
						this.reservePage.elements.bkBtn.classList.add("disable");
					}
				} else {
					this.reservePage.elements.lastMsg.style.display = "block";
					this.reservePage.elements.btnAgree.checked = false;
					this.reservePage.elements.bkBtn.classList.add("disable");
				}
			}.bind(this));
		}.bind(this),

		setEventToBtnReserve : function(){
			this.reservePage.elements.bkBtn.addEventListener("click", function(event){
				if(!event.target.parentNode.classList.contains("disable")){
					this.reservePage.ajax.sendPost("/reservation/api/reservations", this.reservePage.ajaxOptions.getOptionsForMakeReservation());
				}
			}.bind(this));
		}.bind(this),
		
		setPrevPageLink: function(){
			this.reservePage.elements.btnBack.addEventListener("click", function(){
				history.back();
			});
		}.bind(this),
		
		scrollTop: function(){
			addScrollTopEvent(this.reservePage.elements.btnTop);
		}.bind(this),
	}
}