function ajax(method ,url, data, callback) {
	var xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.addEventListener("load", callback);
	xmlHttpRequest.open(method, url + "?" + data);
	xmlHttpRequest.send(data);
}