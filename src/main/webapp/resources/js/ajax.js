function ajax(method ,url, data, callback) {
	var xmlHttpRequest = new XMLHttpRequest();
	
	xmlHttpRequest.addEventListener("load", (response) =>{
		var responseData = response.currentTarget.responseText;
		callback(responseData);
	});
	
	xmlHttpRequest.open(method, url + "?" + data);
	xmlHttpRequest.send(data);
}