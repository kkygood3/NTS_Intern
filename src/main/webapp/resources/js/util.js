/**
 * @author 육성렬
 */

/**
 * @function sendGet 서버에 Get 메서드 요청 함수.
 * @param {String}
 *            path 요청 URL
 * @param {JSON}
 *            params value 값에 Array 형태 넣지말것.
 * @param {Function}
 *            onCallback 콜백 함수.
 */
function sendGet(path, params, onCallback) {
	var data = "";
	if (params) {
		data = Object.keys(params).map(function(key) {
			return key + "=" + encodeURIComponent(params[key]);
		}).join("&");
	}
	var request = new XMLHttpRequest();
	request.addEventListener("load", function(event) {
		onCallback(event.target);
	});

	var url = path + (data.length == 0 ? "" : "?") + data;
	request.open("GET", url);
	request.setRequestHeader("Content-type", "charset=utf-8");
	request.send();
}

/**
 * @function getDisplayInfoIdFromUrl url경로에서 displayInfoId 얻음.
 */
function getDisplayInfoIdFromUrl () {
	var path = window.location.pathname;
	var splitPath = path.split("/");
	return splitPath[splitPath.length-1];
}

/**
 * @function getTargetTemplate id를 받아 template을 바인딩하는 함수를 리턴해준다.
 * @param {string} templateId  #아이디 형식을 보낼 것
 * @return {function}  html template를 바인딩하는 함수 리턴
 */
function getTargetTemplate (templateId){
	var template = document.querySelector(templateId).innerText;
	return Handlebars.compile(template);
}

/**
 * @function toDateString timestamp를 등록 날짜 형식으로 변환해줌.
 * @param {Number} timestamp 
 */
function toDateString (timestamp) {
	var date = new Date(timestamp);
	return date.getUTCFullYear() + "." + (date.getMonth() + 1) + "." +date.getDay() + "."
}