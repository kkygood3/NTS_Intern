function AjaxSender(){}
AjaxSender.prototype.send = function(url, options){
	var httpRequest;
	
	if (window.XMLHttpRequest) {
		httpRequest =  new XMLHttpRequest();
		
		httpRequest.onreadystatechange = function() {
			var jsonResponse;
			
			if (httpRequest.readyState === 4 && httpRequest.status === 200) {
				if(typeof(options.callback) === "function" && options.callback !== null && options.callback !== undefined){
					jsonResponse = JSON.parse(httpRequest.responseText);
					
					options.callback(jsonResponse);
					
				}
			}
		};
		
		httpRequest.open(this.method, url);
		httpRequest.setRequestHeader("Content-type", options.contentType);
		if(options.data === undefined || options.data === null){
			httpRequest.send();
		} else {
			httpRequest.send(options.data);
		}
	}
}
AjaxSender.prototype.sendGet = function(url, options){
	this.method = "GET";
	this.send(url, options);
}
AjaxSender.prototype.sendPost = function(url, options){
	this.method = "POST";
	this.send(url, options);
}
AjaxSender.prototype.sendPut = function(url, options){
	this.method = "PUT";
	this.send(url, options);
}

function InputTagValidator(){
	this.isValid = false;
	this.nameRegex = /(^[가-힣]{2,}$|^[a-zA-Z]{3,}$)/;
	this.telRegex = /^([0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}|[0-9]{4}-[0-9]{4})$/
	this.emailRegex = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.(com|net|co\.kr)$/;
}
InputTagValidator.prototype.validateInputTag = function(inputTag, regularExpression){
	inputTag.addEventListener("input", function(event){
		if(regularExpression === this.telRegex) {
			event.target.value = this.setTelNumberformat(event.target.value);
		}
		
		if(regularExpression.test(event.target.value)){
			this.isValid = true;
		} else {
			this.isValid = false;
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

var addScrollTopEvent = function(btnElement){
	btnElement.addEventListener("click", function(){
		event.preventDefault();
		document.documentElement.scrollTop = 0;
	});
}