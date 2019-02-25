function Ajax(){
	
}

Ajax.prototype = {
	_HTTP_STATUS : {
		_OK : 200,
		_NOT_FOUND : 404,
		_BAD_REQUEST : 400,
		_INTERNAL_SERVER : 500,
	},
	get : function(url, callback) {
		var xmlHttpRequest = new XMLHttpRequest();
		
		xmlHttpRequest.addEventListener("load", (evt) =>{
			var response = evt.currentTarget;
			var responseObj = JSON.parse(response.responseText);
			if(responseObj.httpStatusCode === this._HTTP_STATUS._OK){
				callback(responseObj);
			}
			else{
				this._printError(responseObj);
			}
		});
		
		xmlHttpRequest.open("GET", url);
		xmlHttpRequest.send();
	},
	put : function(url, callback){
		var xmlHttpRequest = new XMLHttpRequest();
		
		xmlHttpRequest.addEventListener("load", (evt) =>{
			var response = evt.currentTarget;
			var responseObj = JSON.parse(response.responseText);
			if(responseObj.httpStatusCode === this._HTTP_STATUS._OK){
				callback(responseObj);
			}
			else{
				this._printError(responseObj);
			}
		});
		
		xmlHttpRequest.open("PUT", url);
		xmlHttpRequest.send();
	},
	post : function(url, data, callback){
		var xmlHttpRequest = new XMLHttpRequest();
		
		xmlHttpRequest.addEventListener("load", (evt) =>{
			var response = evt.currentTarget;
			var responseObj = JSON.parse(response.responseText);
			if(responseObj.httpStatusCode === this._HTTP_STATUS._OK){
				callback(responseObj);
			}
			else{
				this._printError(responseObj);
			}
		});
		
		xmlHttpRequest.open("POST", url);
		xmlHttpRequest.send(data);
	},
	postWithJson : function(url, data, callback){
		var xmlHttpRequest = new XMLHttpRequest();
		
		xmlHttpRequest.addEventListener("load", (evt) =>{
			var response = evt.currentTarget;
			var responseObj = JSON.parse(response.responseText);
			if(responseObj.httpStatusCode === this._HTTP_STATUS._OK){
				callback(responseObj);
			}
			else{
				this._printError(responseObj);
			}
		});
		
		xmlHttpRequest.open("POST", url);
		xmlHttpRequest.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
		xmlHttpRequest.send(data);
	},
	_printError : function(responseObj){
		console.error("data load fail cause : [http_status : %s, code : %s, message : %s]", 
				responseObj.httpStatus,
				responseObj.httpStatusCode,
				responseObj.message);
	}
}

