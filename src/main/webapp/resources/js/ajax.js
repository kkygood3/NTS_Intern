function ajaxGet(url, callback) {
	var xmlHttpRequest = new XMLHttpRequest();
	
	xmlHttpRequest.addEventListener("load", (evt) =>{
		var response = evt.currentTarget;
		if(response.status === HTTP_STATUS.OK){
			var responseData = response.responseText;
			callback(responseData);
		}
		else if(response.status === HTTP_STATUS.NOT_FOUND){
			alert("Data를 찾을 수 없습니다.");
		}
	});
	
	xmlHttpRequest.open("GET", url);
	xmlHttpRequest.send();
}

function ajaxPostJSON(url, data, callback){
	var xmlHttpRequest = new XMLHttpRequest();
	
	xmlHttpRequest.addEventListener("load", (evt) =>{
		var response = evt.currentTarget;
		if(response.status === HTTP_STATUS.OK){
			var responseData = response.responseText;
			callback(responseData);
		}
		else if(response.status === HTTP_STATUS.NOT_FOUND){
			alert("Data를 찾을 수 없습니다.");
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
		if(response.status === HTTP_STATUS.OK){
			var responseData = response.responseText;
			callback(responseData);
		}
		else if(response.status === HTTP_STATUS.NOT_FOUND){
			alert("Data를 찾을 수 없습니다.");
		}
	});
	
	xmlHttpRequest.open("PUT", url);
	xmlHttpRequest.send();
}

const HTTP_STATUS = {
	OK : 200,
	NOT_FOUND : 404,
	BAD_REQUEST : 400,
	INTERNAL_SERVER : 500,
}