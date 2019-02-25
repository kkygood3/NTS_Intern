/**
 * ajax 처리
 * @author 시윤
 *
 */

/**
 * GET방식으로 ajax 요청
 * @param url 요청할 url
 * @param action 응답받은후 실행할 함수
 */
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

function sendPostAjax(sendUrl, data, action) {
	$.ajax({
		method: "POST",
		url: sendUrl,
		data: JSON.stringify(data),
		dataType: "json",
		contentType : "application/json",
		success: function(response){
			action(response);
	    }
	});
}

function sendPutAjax(sendUrl, action, params) {
	$.ajax({
		method: "PUT",
		url: sendUrl,
		success: function(response){
			action(response, params);
        }
	});
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
