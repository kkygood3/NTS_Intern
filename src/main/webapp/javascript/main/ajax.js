function sendGETAjax(url, action) {
	var httpRequest = new XMLHttpRequest();
	
	if(!httpRequest) {
		return false;
	}
	httpRequest.open("GET", url);
	httpRequest.onreadystatechange = function getPromotions() {
		if (httpRequest.readyState === XMLHttpRequest.DONE) {
			if (httpRequest.status === 200) {
			    var resultText = JSON.parse(httpRequest.responseText);
			    action(resultText);
			} else {
				return false;
			}
		} else {
			return false;
		}
	};
	httpRequest.send();
}
