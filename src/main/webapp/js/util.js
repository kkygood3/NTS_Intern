function AjaxSend(){}
AjaxSend.prototype.send = function(url, options){
	var httpRequest;
	
	if (window.XMLHttpRequest) {
		httpRequest =  new XMLHttpRequest();
		
		httpRequest.onreadystatechange = function() {
			var jsonResponse;
			
			if (httpRequest.readyState === 4 && httpRequest.status === 200) {
				if(typeof(options.callBack) === "function" && options.callBack !== null && options.callBack !== undefined){
					jsonResponse = JSON.parse(httpRequest.responseText);
					
					options.callBack(jsonResponse);
					
				}
			}
		};
		
		httpRequest.open(this.method, url);
		httpRequest.setRequestHeader("Content-type", options.contentType);
		if(options.data === undefined || options.data === null){
			httpRequest.send();
		} else {
			httpRequest.send(this.data);
		}
	}
}
AjaxSend.prototype.sendGet = function(url, options){
	this.method = "GET";
	this.send(url, options);
}
AjaxSend.prototype.sendPost = function(url, options){
	this.method = "POST";
	this.send(url, options);
}
AjaxSend.prototype.sendPut = function(url, options){
	this.method = "PUT";
	this.send(url, options);
}

var ajaxSend = function(method, url, callBack, contentType, data){
	var httpRequest;
	
	if (window.XMLHttpRequest) {
		httpRequest =  new XMLHttpRequest();
		
		httpRequest.onreadystatechange = function() {
			var jsonResponse;
			
			if (httpRequest.readyState === 4 && httpRequest.status === 200) {
				if(typeof(callBack) === "function" && callBack !== undefined && callBack !== null){
					jsonResponse = JSON.parse(httpRequest.responseText);
					
					callBack(jsonResponse);
				}
			}
		}
		
		httpRequest.open(method, url);
		httpRequest.setRequestHeader("Content-type", contentType);
		if(data === undefined || data === null){
			httpRequest.send();
		} else {
			httpRequest.send(data);
		}
	}
}

var addScrollTopEvent = function(btnElement){
	btnElement.addEventListener("click", function(){
		event.preventDefault();
		document.documentElement.scrollTop = 0;
	});
}