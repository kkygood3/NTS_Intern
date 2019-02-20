const REG_TELEPHONE = /^\d{2,3}-\d{3,4}-\d{4}$/;
const REG_EMAIL = /^[_a-zA-Z0-9-\.]+@[\.a-zA-Z0-9-]+\.[a-zA-Z]+$/;

const DAY_LABEL = ['일','월','화','수','목','금','토'];

/**
 * 반복적으로 호출하는 XMLHttpRequest를 전담하는 함수
 * 
 * @param 페이지
 *            로드후에 실행할 callback function. 매개변수로 response를 가져아 함
 * @param api
 *            URL
 * @method GET, POST, PUT, DELETE
 */

function requestAjax(callback, url, method, param) {
	if (!method) {
		method = 'GET';
	}
	var ajaxReq = new XMLHttpRequest();
	ajaxReq.callback = callback;
	ajaxReq.addEventListener('load', function(evt) {
		this.callback(evt.target.response);
	});
	ajaxReq.open(method, url);
	ajaxReq.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
	ajaxReq.responseType = 'json';
	ajaxReq.send(param)
}

/**
 * URL parameter의 value를 얻어내기 위한 함수
 * 
 * @param parameter
 *            name
 * @return parameter value
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