const REG_TELEPHONE = /^\d{2,3}-\d{3,4}-\d{4}$/;
const REG_EMAIL = /^[_a-zA-Z0-9-\.]+@[\.a-zA-Z0-9-]+\.[a-zA-Z]+$/;

const DAY_LABEL = ['일','월','화','수','목','금','토'];

/**
 * XMLHttpRequest전담 함수
 * @param callback
 * @param url
 * @param method(GET, POST, PUT, DELETE)
 * @param param
 */
function requestAjax(callback, url, method, param, json) {
	if (!method) {
		method = 'GET';
	}
	var ajaxReq = new XMLHttpRequest();
	ajaxReq.callback = callback;
	ajaxReq.addEventListener('load', function(evt) {
		this.callback(evt.target.response);
	});
	ajaxReq.open(method, url);
	ajaxReq.responseType = 'json';
	
	if (!json){
		ajaxReq.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
	}
	
	ajaxReq.send(param)
}

/**
 * URL parameter의 value를 얻어내기 위한 함수
 */
function getUrlParameter(name) {
	var params = location.href.split('?')[1].split('&');
	for (var i = 0; i < params.length; i++) {
		var paramSplited = params[i].split('=');
		var paramName = paramSplited[0];
		var paramValue = paramSplited[1];

		if (paramName === name) {
			return paramValue;
		}
	}
}

/**
 * Input 생성자
 * 
 * @param name
 * @param value
 */
function DefaultInput(name, value) {
	this.input = document.createElement('input');
	this.input.setAttribute("name", name);
	this.input.setAttribute("value", value);
}