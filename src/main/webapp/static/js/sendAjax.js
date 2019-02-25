/**
 * @desc sendAjax 비동기 요청 ( 공통 함수 )
 * @param sendHeader ( method, uri)
 * @param sendData ( 보낼 데이터 )
 * @param callback ( 데이터를 가지고 온뒤 수행할 callback 함수)
 * @returns xhr.response ( JSON 형태로 parsing )
 */
function sendAjax(sendHeader, sendData, callback, failCallback) {
	var xhr = new XMLHttpRequest();

	xhr.open(sendHeader.method, sendHeader.uri, false);

	if(sendHeader.method === 'GET'){
		xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	} else {
		xhr.setRequestHeader("Content-type","application/json");
	}
	

	xhr.onreadystatechange = function() {
		
		if (xhr.readyState === xhr.DONE && xhr.status === 200) {
			callback.apply(this, [ JSON.parse(xhr.response) ]);
		} else if (xhr.status === 400){
			failCallback();
		}
	}
	xhr.send(sendData);
}

module.exports = sendAjax;