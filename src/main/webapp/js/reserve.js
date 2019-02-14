document.addEventListener("DOMContentLoaded", function() {
	bookingPage.getBookingPage(bookingPage.displayInfoId);
});

var bookingPage = {
	getBookingPage: function(displayInfoId){
		var httpRequest;
		
		this.compileHendlebars.compareDiscountRateToZero();
		
		this.compileHendlebars.convertTypeName();
		
		if (window.XMLHttpRequest) {
			httpRequest =  new XMLHttpRequest();
			
			httpRequest.onreadystatechange = function() {
				var jsonResponse;
				
				if (httpRequest.readyState === 4 && httpRequest.status === 200) {
					jsonResponse = JSON.parse(httpRequest.responseText);
					
					this.renderDisplayInfo(jsonResponse);
					this.displayTicketPrice(jsonResponse);
				}
			}.bind(this)
			
			httpRequest.open("GET", "../../api/products/" + displayInfoId);
			httpRequest.setRequestHeader("Content-type", "charset=utf-8");
			httpRequest.send();
		}
		
		this.setEvent.validateInputValue(this.elements.bkName, /(^[가-힣]{2,}$|^[a-zA-Z]{3,}$)/);
		this.setEvent.validateInputValue(this.elements.bkTel, /^[0-9]{9,}$/);
		this.setEvent.validateInputValue(this.elements.bkEmail, /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.(com|net|co\.kr)$/);
		
		this.setEvent.changeTicketCount();
		this.setEvent.openTerms();
		this.setEvent.acceptTerms();
		this.setEvent.goToPrevPage();
		this.setEvent.scrollTop();
	},
	
	displayInfoId : window.location.href.match(/detail\/\d+/)[0].split("/")[1],
	
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
		
		btnShowMore : document.querySelectorAll(".btn_agreement"),
		btnAgree : document.querySelector(".chk_agree")
	},
	
	container: {
		displayInfoContainer : document.querySelector(".store_details"),
		priceContainer : document.querySelector(".ticket_body"),
		bkBtnContainer : document.querySelector(".bk_btn_wrap") 
	},
	
	template: {
		displayInfoTemplate : document.querySelector("#diplayInfoTemplate").innerHTML,
		priceTemplate : document.querySelector("#priceTemplate").innerHTML,
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
		
		convertTypeName: function(){
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
	
	renderDisplayInfo: function(jsonResponse){
		var bindDisplayInfo = this.compileHendlebars.bindTemplate(this.template.displayInfoTemplate);
		
		this.elements.title.innerHTML = jsonResponse["displayInfo"].productDescription;
		this.elements.mainImage.src = "../../" + jsonResponse["productImages"][0].saveFileName;
		this.container.displayInfoContainer.innerHTML = bindDisplayInfo(jsonResponse);
	},
	
	displayTicketPrice: function(jsonResponse){
		var bindTicketPrice = this.compileHendlebars.bindTemplate(this.template.priceTemplate);
		
		this.container.priceContainer.innerHTML = bindTicketPrice(jsonResponse);
		
		this.elements.ticketCounts = document.querySelectorAll(".count_control_input");
		this.setAmountOfPayment.checkTicketCount();
	},
	
	setEvent: {
		changeTicketCount: function(){
			this.bookingPage.container.priceContainer.addEventListener("click", function(event){
				if(!event.target.classList.contains("disabled")){
					this.bookingPage.elements.btnAgree.checked = false;
					this.bookingPage.container.bkBtnContainer.classList.add("disable");
				};
				
				this.bookingPage.setAmountOfPayment.changeTicketCount(event);
			}.bind(this));
		}.bind(this),
		
		validateInputValue: function(inputTag, regularExpression){
			inputTag.addEventListener("input", function(event){
				this.bookingPage.elements.btnAgree.checked = false;
				this.bookingPage.container.bkBtnContainer.classList.add("disable");
				
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
		
		openTerms: function(){
			this.bookingPage.elements.btnShowMore.forEach(function(btnOpen){
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
		
		acceptTerms: function(){
			var bkName = this.bookingPage.elements.bkName;
			var bkTel = this.bookingPage.elements.bkTel;
			var bkEmail = this.bookingPage.elements.bkEmail;
			var ticketCnt = this.bookingPage.elements.totalCount;
			var lastMsg = this.bookingPage.container.bkBtnContainer.parentNode.querySelector(".warning_msg");
			
			this.bookingPage.elements.btnAgree.addEventListener("change", function(){
				if(bkName.classList.contains("valid_value")
					&& bkTel.classList.contains("valid_value")
					&& bkEmail.classList.contains("valid_value")
					&& ticketCnt.value !== 0){

					lastMsg.style.display = "none";
					
					if(this.bookingPage.container.bkBtnContainer.classList.contains("disable")){
						this.bookingPage.container.bkBtnContainer.classList.remove("disable");
					} else {
						this.bookingPage.container.bkBtnContainer.classList.add("disable");
					}
				} else {
					lastMsg.style.display = "block";
					this.bookingPage.elements.btnAgree.checked = false;
					this.bookingPage.container.bkBtnContainer.classList.add("disable");
				}
			}.bind(this));
		}.bind(this),
		
		goToPrevPage: function(){
			this.bookingPage.elements.btnBack.addEventListener("click", function(){
				event.preventDefault();
				history.back();
			});
		}.bind(this),
		
		scrollTop: function(){
			this.bookingPage.elements.btnTop.addEventListener("click", function(){
				event.preventDefault();
				document.documentElement.scrollTop = 0;
			});
		}.bind(this)
	},
	
	setAmountOfPayment: {
		amountOfPayment: function(){
			var amountOfPayment = 0;
			
			document.querySelectorAll(".total_price").forEach(function(price){
				amountOfPayment += parseInt(price.innerHTML);
			});
			
			return amountOfPayment;
		},
		
		checkTicketCount: function(){
			var totalCount = 0;
			this.bookingPage.elements.ticketCounts.forEach(function(ticketCnt){
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
			
			this.bookingPage.elements.totalCount.value = totalCount;
			if(totalCount === 0) {
				this.bookingPage.elements.totalCount.innerHTML = "선택된 티켓이 없습니다.";
			} else {
				this.bookingPage.elements.totalCount.innerHTML = "총 " + totalCount + "매, " + this.bookingPage.setAmountOfPayment.amountOfPayment() + "원"
			}
		}.bind(this),
		
		changeTicketCount: function(event){
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
	}
}