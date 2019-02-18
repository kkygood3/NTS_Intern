/**
 * product_price의 product_type_name에 대응하는 한글 매핑
 * 없는 문자가 들어오면 문자에 '석'을 붙여서 사용
 */
const PRICE_TYPE ={
	    A : "성인",
	    Y : "청소년",
		B : "유아",
	    S : "셋트",
	    D : "장애인",
	    C : "지역주민",
	    E : "얼리버드",
	    V : "VIP",
	    D : "평일"
	}

/**
 * 반복적으로 호출하는 XMLHttpRequest를 전담하는 함수
 * @param 페이지 로드후에 실행할 callback function. 매개변수로 response를 가져아 함 
 * @param api URL
 * @method GET, POST, PUT, DELETE
 */

function requestAjax(callback, url, method) {
	if(!method){
		method = 'GET';
	}
	var ajaxReq = new XMLHttpRequest();
	ajaxReq.callback = callback;
	ajaxReq.addEventListener('load', function(evt) {
		this.callback(evt.target.response);
	});
	ajaxReq.open(method, url);
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
/**
 * priceTypeName을 실제 출력하는 문자로 변환
 * example) A -> 성인
 * @param priceTypeName - 타입을 나타내는 영문자
 * @return 변환 값
 */
function getTypeLabel(priceTypeName){
	var priceType = PRICE_TYPE[priceTypeName];
	
	if(!priceType){
		priceType = priceTypeName + '석';
	}
	
	return priceType
}


/**
 * Input 생성자
 * @param name
 * @param value
 */
function DefaultInput(name, value){
	this.input = document.createElement('input');
	this.input.setAttribute("name", name);
	this.input.setAttribute("value", value);
}