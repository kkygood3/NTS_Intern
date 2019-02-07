function sendGETAjax(url, action) {
	var httpRequest = new XMLHttpRequest();
	
	if(!httpRequest) {
		return false;
	}
	httpRequest.open("GET", url);
	httpRequest.onreadystatechange = function getPromotions() {
		if (httpRequest.readyState !== XMLHttpRequest.DONE) {
			return false;
		}
		if (httpRequest.status !== 200) {
			window.location.href = "/error" + getParamsForErrorPage(httpRequest.status);
			return false;
		}
		alert(httpRequest.getAllResponseHeaders());
		var resultText = JSON.parse(httpRequest.responseText);
		action(resultText);
	};
	httpRequest.send();
}

function getParamsForErrorPage(status) {
	return "?code=" + status + "&message=" + getErrorText(status);
}
function getErrorText(status) {
	switch(status) {
	  case 400:
		  return "Bad Request";
	  case 404:
		  return "Page Not Found";
	  default:
		  return "Unknown Error";
	}
}
