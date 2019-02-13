/**
 * 메인 페이지에서 호출할 /api의 Ajax Reqeust를 전담.
 * 
 * @param callback 페이지 로드후에 실행할 callback function
 * @param url
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