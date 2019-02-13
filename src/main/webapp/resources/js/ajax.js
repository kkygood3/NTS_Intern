function ajax(method ,url, data, callback) {
	var xmlHttpRequest = new XMLHttpRequest();
	
	xmlHttpRequest.addEventListener("load", (evt) =>{
		var response = evt.currentTarget;
		if(response.status === HTTP_STATUS.OK){
			var responseData = response.responseText;
			callback(responseData);
		}
		else if(response.status === HTTP_STATUS.NOT_FOUND){
			alert("Data를 찾을 수 없습니다.");
			location.href="/main";
		}
	});
	
	xmlHttpRequest.open(method, url + "?" + data);
	xmlHttpRequest.send(data);
}

const HTTP_STATUS = {
	OK : 200,
	NOT_FOUND : 404,
	BAD_REQUEST : 400,
	INTERNAL_SERVER : 500,
}