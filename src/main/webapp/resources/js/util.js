/**
 * @author 육성렬
 */

/**
 * @function sendGet 서버에 Get 메서드 요청 함수.
 * @param {String}
 *            path 요청 URL
 * @param {JSON}
 *            params value 값에 Array 형태 넣지말것.
 * @param {JSON}
 * 			headers 세팅을 위한 매개변수 Default로 "Content-type", "charset=utf-8" 가 설정된다.
 * @param {Function}
 *            onCallback 콜백 함수.
 */
function sendGet(path, params, headers, onCallback) {
    var data = "";
    if (params) {
        data = Object.keys(params)
            .map(function(key) {
                return key + "=" + encodeURI(params[key]);
            })
            .join("&");
    }
    var request = new XMLHttpRequest();
    request.addEventListener("load", function(event) {
        onCallback(event.target);
    });

    var url = path + (data.length == 0 ? "" : "?") + data;
    request.open("GET", url);
    request.setRequestHeader("Content-type", "charset=utf-8");
    if (headers) {
        Object.keys(headers).map(function(key) {
            request.setRequestHeader(key, headers[key]);
        });
    }
    request.send();
}

/**
 * @function sendPostWithJson 서버에 Post 메서드 요청 함수. 바디는 JSON
 * @param {String}
 *            path 요청 URL
 * @param {JSON}
 *            params value
 * @param {Function}
 *            onCallback 콜백 함수.
 */
function sendPostWithJson(path, params, onCallback) {
    var request = new XMLHttpRequest();
    request.addEventListener("load", function(event) {
        onCallback(event.target);
    });
    request.open("POST", path);
    request.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
    request.send(JSON.stringify(params));
}

function sendPutWithPathVariable(path, variable, onCallback) {
    var request = new XMLHttpRequest();
    request.addEventListener("load", function(event) {
        onCallback(event.target);
    });

    request.open("PUT", path + "/" + variable);
    request.setRequestHeader("Content-Type", "charset=UTF-8");
    request.send();
}
/**
 * @function getTargetTemplate id를 받아 template을 바인딩하는 함수를 리턴해준다.
 * @param {string} templateId  #아이디 형식을 보낼 것
 * @return {function}  html template를 바인딩하는 함수 리턴
 */
function getTargetTemplate(templateId) {
    var template = document.querySelector(templateId).innerText;
    return Handlebars.compile(template);
}

/**
 * @function toDateString timestamp를 등록 날짜 형식으로 변환해줌.
 * @param {Number} timestamp
 */
function toDateString(timestamp) {
    var date = new Date(timestamp);
    return date.getUTCFullYear() + "." + (date.getMonth() + 1) + "." + date.getDate() + ".";
}

/**
 * @function convertFormattedNumberToNumber 천자리 단위로 콤마가 찍힌 포맷의 숫자를 Number로 리턴해준다.
 * @param {String} formattedNumber "1,000,000" 와 같이 천자리 단위로 콤마가 찍힌 포맷.
 */
function convertFormattedNumberToNumber(formattedNumber) {
    return Number(formattedNumber.replace(/,/gi, ""));
}

/**
 * @function convertNumberToFormattedNumber 숫자를 천자리 단위로 콤마가 찍힌 포맷의 문자로 리턴해준다.
 * @param {Number} number
 */
function convertNumberToFormattedNumber(number) {
    return Intl.NumberFormat().format(number);
}

/**
 * @function getEmailRegex Email 정규식 값을 받는다.
 */
function getEmailRegex() {
    return /^[a-zA-Z0-9]{1,20}@[a-zA-Z0-9]{1,20}\.[a-zA-Z]{1,6}\.[a-zA-Z]{1,6}$|^[a-zA-Z0-9]{1,20}@[a-zA-Z0-9]{1,20}\.[a-zA-Z]{1,6}$/;
}

/**
 * @function getPhoneNumberRegex 휴대폰 번호 정규식 값을 받는다.
 */
function getPhoneNumberRegex() {
    return /01\d\-\d{3,4}\-\d{4}$/;
}

/**
 * @function getUsernameRegex 예매자 이름 정규식 값을 받는다.
 */
function getUsernameRegex() {
    return /^[가-힣A-Za-z0-9]{1,17}$/;
}
