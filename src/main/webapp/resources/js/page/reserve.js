document.addEventListener("DOMContentLoaded", () => {
	
	template.setting();

	var displayInfoId = document.querySelector("#display_info").dataset.displayInfoId;	
	ajaxGet("/api/product-prices/"+displayInfoId, printProductPrice);
	
	settingDataVaildObserver();
	
	addEventInputTelephone();
	addEventInputEmail();
	addEventInputName();
	addEventAgreementInput();
	addEventAgreementContentFoldExpand();
	addEventClickSubmit();
});

var productPriceObjectList = new function(){
	this.list = [];
	
	this.add = function(productPrice){
		this.list.push(productPrice);
	};
	
	this.toReservationData = function(){
		var productPriceObjArr = [];
		this.list.forEach((productPrice) =>{
			var productPriceObj = {};
			productPriceObj.productPriceId = productPrice.productPriceId;
			productPriceObj.count = productPrice.count;
			
			productPriceObjArr.push(productPriceObj);
		});
		
		return productPriceObjArr;
	};
}();

function printProductPrice(data){
	var responseObject = JSON.parse(data);
	
	var productPriceList = document.querySelector("#product_price_list");
	
	var productDisplay = responseObject.productDisplay;
	var productPriceItems = responseObject.productPriceList;
	var productReservationDate = responseObject.reservationDate;
	
	printProductDisplay(productDisplay, productReservationDate);
	
	productPriceItems.forEach((productPriceItem) => {
		var productPriceObject = new ProductPrice(productPriceItem);
		productPriceObjectList.add(productPriceObject);
		productPriceList.appendChild(productPriceObject.element);
	});
	
	
}

function printProductDisplay(productDisplay, productReservationDate){
	document.querySelector("#productDisplayImg").src = "/resources/" + productDisplay.productDisplayImageUrlList[0];
	document.querySelector("#productDescription").innerText = productDisplay.productDescription;
	document.querySelector("#productId").value = productDisplay.productId;
	document.querySelector("#displayInfoId").value = productDisplay.displayInfoId;
	document.querySelector("#reservation_date").innerText = productReservationDate;
	document.querySelector("#date").value = productReservationDate;
}

function addEventInputTelephone(){
	var inputTelephone = document.querySelector("#tel");
	var telWarningMsg = document.querySelector("#tel_warning");
	
	inputTelephone.addEventListener("blur", () =>{
		var tel = inputTelephone.value;
		const telRegex = /01[01789]-\d{3,4}-\d{4}/;
		if(tel === "" || tel.match(telRegex) === null
				|| tel.match(telRegex)[0] !== tel){
			telWarningMsg.style.visibility = "visible";
			inputTelephone.dataset.validFlag = "false";
		}
		else{
			inputTelephone.dataset.validFlag = "true";
		}
	});
	
	inputTelephone.addEventListener("focus",() =>{
		telWarningMsg.style.visibility = "hidden";
	});
	
	telWarningMsg.addEventListener("click", () =>{
		inputTelephone.focus();
	});
		
}

function addEventInputEmail(){
	var inputEmail = document.querySelector("#email");
	var emailWarningMsg = document.querySelector("#email_warning");
	
	inputEmail.addEventListener("blur", () =>{
		var email = inputEmail.value;
		const emailRegex = /[.\-\w]+@\w+[.]?[\-\w]+[.]?[\w]+/;
		if(email === "" || email.match(emailRegex) === null
				|| email.match(emailRegex)[0] !== email){
			emailWarningMsg.style.visibility = "visible";
			inputEmail.dataset.validFlag = "false";
		}
		else{
			inputEmail.dataset.validFlag = "true";
		}
	});
	
	inputEmail.addEventListener("focus", () =>{
		emailWarningMsg.style.visibility = "hidden";
	});
	
	emailWarningMsg.addEventListener("click", () =>{
		inputEmail.focus();
	});
}

function addEventInputName(){
	var inputName = document.querySelector("#name");
	
	inputName.addEventListener("change", ()=>{
		var name = inputName.value;
		if(name === ""){
			inputName.dataset.validFlag = "false";
		}
		else{
			inputName.dataset.validFlag = "true";
		}
	});
}

function addEventAgreementInput(){
	var inputAgreement = document.querySelector("#agreement");
	
	inputAgreement.addEventListener("change", ()=>{
		inputAgreement.dataset.validFlag = inputAgreement.checked;
	});
}

function addEventAgreementContentFoldExpand(){
	var privacyCollectContent = document.querySelector("#privacy_collect_content");
	var btnPrivacyCollectContent = document.querySelector("#btn_privacy_collect_content");
	
	var privacyUseOtherContent = document.querySelector("#privacy_use_other_content");
	var btnPrivacyUseOtherContent = document.querySelector("#btn_privacy_use_other_content");
	
	addEventClickAgreementFoldExpand(privacyCollectContent, btnPrivacyCollectContent);
	addEventClickAgreementFoldExpand(privacyUseOtherContent, btnPrivacyUseOtherContent);
}

function addEventClickAgreementFoldExpand(content, button){
	button.addEventListener("click", () =>{
		const OPEN_CLASS = " open ";
		if(content.className.includes(OPEN_CLASS)){
			content.className = content.className.replace(OPEN_CLASS, "");
			button.querySelector("span").innerText = "보기";
			button.querySelector("i").className = "fn fn-down2";
		}
		else{
			content.className += OPEN_CLASS;
			button.querySelector("span").innerText = "접기";
			button.querySelector("i").className = "fn fn-up2";
		}
	});
}

function addEventClickSubmit(){
	var btnSubmit = document.querySelector("#btn_submit");
	
	btnSubmit.addEventListener("click", () =>{
		var formData = new FormData(document.querySelector("#reservation_form"));
		
		var data = {
			productId : formData.get("productId"),
			displayInfoId : formData.get("displayInfoId"),
			reservationName : formData.get("name"),
			reservationEmail : formData.get("email"),
			reservationTel : formData.get("tel"),
			reservationDate : formData.get("date"),
			reservationPriceList : productPriceObjectList.toReservationData(),
		}
		ajaxPostJSON("/api/reservation", JSON.stringify(data), () =>{
			location.href="/main";
		});
	});
}

function ProductPrice(productPriceItem){
	this.productPriceId = productPriceItem.productPriceId;
	this.count = 0;
	this.totalPrice = 0;
	this.price = productPriceItem.price;
	this.element = template.parseProductPriceToElement(productPriceItem);
	this.countElement = this.element.querySelector("[data-name=count]")
	this.btnPlusElement = this.element.querySelector("[data-name=plus]");
	this.btnMinusElement = this.element.querySelector("[data-name=minus]");
	this.totalPriceElement = this.element.querySelector("[data-name=total_price]");
	
	this.addEvnetClickPlusMinus();
	this.render();
}

ProductPrice.prototype = {
		
	render : function(){
		this.canUsedMinusButton();
		this.countElement.value = this.count;
		this.totalPriceElement.innerText = this.count * this.price;
		this.updateTotalCount();
	},
	addEvnetClickPlusMinus : function(){
		
		this.btnPlusElement.addEventListener("click", () =>{
			this.count++;
			this.totalPrice = this.count * this.price;
			this.render();
		});
		
		this.btnMinusElement.addEventListener("click", () =>{
			this.count--;
			this.totalPrice = this.count * this.price;
			this.render();
		});
	},
	canUsedMinusButton : function(){
		const BUTTON_DISABLE_AND_NO_EVENT =" disabled no_event";
		if(this.count < 1){
			this.btnMinusElement.className += BUTTON_DISABLE_AND_NO_EVENT;
		}
		else{
			this.btnMinusElement.className = this.btnMinusElement.className.replace(BUTTON_DISABLE_AND_NO_EVENT, "");
		}
	},
	updateTotalCount : function(){
		var productPricesCount = document.querySelectorAll("[data-name=count]");
		var totalCount = document.querySelector("#total_count");
		
		var count = 0;
		productPricesCount.forEach((productPriceCount) =>{
			count += Number(productPriceCount.value);
		});
		
		totalCount.innerText = count;
		totalCount.dataset.validFlag = count > 0 ? "true" : "false";
	}
}

function settingDataVaildObserver(){
	var btnSubmitView = document.querySelector("#btn_submit_view");
	var btnSubmit = document.querySelector("#btn_submit");
	var count = document.querySelector("#total_count");
	var name = document.querySelector("#name");
	var tel = document.querySelector("#tel");
	var email = document.querySelector("#email");
	var agreement = document.querySelector("#agreement");
	
	
	var observer = new DataVaildObserver(btnSubmitView, btnSubmit);
	observer.add(count);
	observer.add(name);
	observer.add(tel);
	observer.add(email);
	observer.add(agreement);
}

function DataVaildObserver(disabledTarget, disabledTargetBtn){
	this.disabledTarget = disabledTarget;
	this.disabledTargetBtn = disabledTargetBtn;
	this.disabledTargetOriginalClass = disabledTarget.className;
	this.targetList = [];
	this.flag = false;
	this.observer = new MutationObserver(this.isVaild.bind(this));
}

DataVaildObserver.prototype = {
	isVaild : function(){
		var invaild = this.targetList.some((target) =>{
			if((target.dataset.validFlag === "false")){
				this.disabledTarget.className = this.disabledTargetOriginalClass;
				this.disabledTargetBtn.disabled = true;
				return true;
			}
			return false;
		});
		if(!invaild){
			this.disabledTarget.className = this.disabledTarget.className.replace("disable", "");
			this.disabledTargetBtn.disabled = false;
		}
	},
	add : function(target){
		const CONFIG = {attributes : true};
		this.targetList.push(target);
		this.observer.observe(target, CONFIG);
	}
}