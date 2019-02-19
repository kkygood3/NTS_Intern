function ajaxGet(url, callback) {
	var xmlHttpRequest = new XMLHttpRequest();
	
	xmlHttpRequest.addEventListener("load", (evt) =>{
		var response = evt.currentTarget;
		if(response.status === HTTP_STATUS._OK){
			var responseData = response.responseText;
			callback(responseData);
		}
	});
	
	xmlHttpRequest.open("GET", url);
	xmlHttpRequest.send();
}

function ajaxPostJSON(url, data, callback){
	var xmlHttpRequest = new XMLHttpRequest();
	
	xmlHttpRequest.addEventListener("load", (evt) =>{
		var response = evt.currentTarget;
		if(response.status === HTTP_STATUS._OK){
			var responseData = response.responseText;
			callback(responseData);
		}
	});
	
	xmlHttpRequest.open("POST", url);
	xmlHttpRequest.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	xmlHttpRequest.send(data);
}

function ajaxPut(url, callback){
var xmlHttpRequest = new XMLHttpRequest();
	
	xmlHttpRequest.addEventListener("load", (evt) =>{
		var response = evt.currentTarget;
		if(response.status === HTTP_STATUS._OK){
			var responseData = response.responseText;
			callback(responseData);
		}
	});
	
	xmlHttpRequest.open("PUT", url);
	xmlHttpRequest.send();
}

const HTTP_STATUS = {
	_OK : 200,
	_NOT_FOUND : 404,
	_BAD_REQUEST : 400,
	_INTERNAL_SERVER : 500,
}