
function sendRequest(request, uri, callBack){
	var xhr = new XMLHttpRequest();
	xhr.open(request.method, uri, true);
	xhr.setRequestHeader("Content-Type", request.contentType);

	xhr.onreadystatechange = () => {
		if(xhr.readyState === 4 && xhr.status === 200){
			callBack(JSON.parse(xhr.responseText));
		}
	}
	
	xhr.send(request.queryString);
}