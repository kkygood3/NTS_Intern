/**
 * @author 육성렬
 */


var httpUtil = {
    /**
     * @function sendGet 서버에 Get 메서드 요청 함수.
     * @param {String}
     *            path 요청 URL
     * @param {JSON}
     *            options 해당 부분에 params값과 header 값을 넣으면됨
     *  참고로 params의 value 값에 Array 형태 넣지말것.
     * @param {Function}
     *            onCallback 콜백 함수.
     */
    sendGet: function(path, options, onCallback) {
        var data = "";
        if (options.params) {
            data = Object.keys(options.params)
                .map(function(key) {
                    return key + "=" + encodeURI(options.params[key]);
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
        if (options.headers) {
            Object.keys(options.headers).map(function(key) {
                request.setRequestHeader(key, options.headers[key]);
            });
        }
        request.send();
    },
    /**
     * @function sendPostWithJson 서버에 Post 메서드 요청 함수. 바디는 JSON
     * @param {String}
     *            path 요청 URL
     * @param {JSON}
     *            params value
     * @param {Function}
     *            onCallback 콜백 함수.
     */
    sendPostWithJson: function (path, params, onCallback) {
        var request = new XMLHttpRequest();
        request.addEventListener("load", function(event) {
            onCallback(event.target);
        });
        request.open("POST", path);
        request.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
        request.send(JSON.stringify(params));
    },
    sendPostWithMultiPartForm: function(path, formData, onCallback) {
        var request = new XMLHttpRequest();
        request.addEventListener("load", function(event) {
            onCallback(event.target);
        });
        request.open("POST", path);
        request.send(formData);
    },
    sendPut: function (path, onCallback) {
        var request = new XMLHttpRequest();
        request.addEventListener("load", function(event) {
            onCallback(event.target);
        });
    
        request.open("PUT", path);
        request.setRequestHeader("Content-Type", "charset=UTF-8");
        request.send();
    }
}

const regex = {
    EMAIL_REGEX: /^[a-zA-Z0-9]{1,20}@[a-zA-Z0-9]{1,20}\.[a-zA-Z]{1,6}\.[a-zA-Z]{1,6}$|^[a-zA-Z0-9]{1,20}@[a-zA-Z0-9]{1,20}\.[a-zA-Z]{1,6}$/,
    PHONE_NUMBER_REGEX: /01\d\-\d{3,4}\-\d{4}$/,
    USERNAME_REGEX: /^[가-힣A-Za-z0-9]{1,17}$/,
    IMAGE_CONTENT_TYPE_REGEX: /^image\/.*$/,
    WORD_MAX_400_REGEX: /^(.|\n){5,400}$/
}
Object.freeze(regex);

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
