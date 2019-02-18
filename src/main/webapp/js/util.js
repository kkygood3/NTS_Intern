/**
 * data : required = false
 */
var ajaxSend = function(method, url, callBack, contentType, data){
	var httpRequest;
	
	if (window.XMLHttpRequest) {
		httpRequest =  new XMLHttpRequest();
		
		httpRequest.onreadystatechange = function() {
			var jsonResponse;
			
			if (httpRequest.readyState === 4 && httpRequest.status === 200) {
				jsonResponse = JSON.parse(httpRequest.responseText);

				callBack(jsonResponse);
			}
		}
		
		httpRequest.open(method, url);
		httpRequest.setRequestHeader("Content-type", contentType);
		httpRequest.send(data);
	}
}

var addScrollTopEvent = function(btnElement){
	btnElement.addEventListener("click", function(){
		event.preventDefault();
		document.documentElement.scrollTop = 0;
	});
}