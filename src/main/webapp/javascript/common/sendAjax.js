function sendGetAjax(url, action) {
	var httpRequest = new XMLHttpRequest();
	
	if(!httpRequest) {
		return false;
	}
	httpRequest.open("GET", url);
	httpRequest.onreadystatechange = () => {
		readyStateChanged(httpRequest, action)
	};
	httpRequest.send();
}

function readyStateChanged(httpRequest, action) {
	if (httpRequest.readyState !== XMLHttpRequest.DONE) {
		return false;
	}
	if (httpRequest.status !== 200) {
		window.location.href = "./error" + getParamsForErrorPage(httpRequest.status);
		return false;
	}
	var resultText = JSON.parse(httpRequest.responseText);
	action(resultText);
}
