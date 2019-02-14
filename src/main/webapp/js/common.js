/**
 * 반복적으로 호출하는 XMLHttpRequest를 전담하는 함수
 * @param 페이지 로드후에 실행할 callback function. 매개변수로 response를 가져아 함 
 * @param api URL
 */
function requestAjax(callback, url) {
	var ajaxReq = new XMLHttpRequest();
	ajaxReq.callback = callback;
	ajaxReq.addEventListener('load', function(evt) {
		this.callback(evt.target.response);
	});
	ajaxReq.open('GET', url);
	ajaxReq.responseType = 'json';
	ajaxReq.send()
}

/**
 * URL parameter의 value를 얻어내기 위한 함수
 * @param parameter name
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