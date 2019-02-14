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
		
		this.setEvent.validation(this.elements.bkName, /(^[가-힣]*$|^[a-zA-Z]*$)/);
		this.setEvent.validation(this.elements.bkTel, /^[0-9]{9,}$/);
		this.setEvent.validation(this.elements.bkEmail, /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.(com|net|co\.kr)$/);
		
		this.setEvent.changeTicketCount();
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
		bkEmail : document.querySelector("#email")
	},
	
	container: {
		displayInfoContainer : document.querySelector(".store_details"),
		priceContainer : document.querySelector(".ticket_body")
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
				this.bookingPage.setAmountOfPayment.changeTicketCount(event);
			}.bind(this));
		}.bind(this),
		
		validation: function(inputTag, regularExpression){
			inputTag.addEventListener("input", function(){
				if(this.value.length === 0 || regularExpression.test(this.value)){
					this.classList.add("valid_value");
					this.classList.remove("wrong_value");
					this.parentNode.querySelector(".warning_msg").style.display = "none";
				} else {
					this.classList.add("wrong_value");
					this.classList.remove("valid_value");
					this.parentNode.querySelector(".warning_msg").style.display = "inline";
				}
			});
		},
		
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