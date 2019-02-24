module.exports = {
    /**
     * @function toDateString timestamp를 등록 날짜 형식으로 변환해줌.
     * @param {Number} timestamp
     */
    toDateString: function (timestamp) {
        var date = new Date(timestamp);
        return date.getUTCFullYear() + "." + (date.getMonth() + 1) + "." + date.getDate() + ".";
    },

    /**
     * @function convertFormattedNumberToNumber 천자리 단위로 콤마가 찍힌 포맷의 숫자를 Number로 리턴해준다.
     * @param {String} formattedNumber "1,000,000" 와 같이 천자리 단위로 콤마가 찍힌 포맷.
     */
    convertFormattedNumberToNumber: function (formattedNumber) {
        return Number(formattedNumber.replace(/,/gi, ""));
    },
    /**
     * @function convertNumberToFormattedNumber 숫자를 천자리 단위로 콤마가 찍힌 포맷의 문자로 리턴해준다.
     * @param {Number} number
     */
    convertNumberToFormattedNumber: function convertNumberToFormattedNumber(number) {
        return Intl.NumberFormat().format(number);
    },
    sendPutWithPathVariable: function (path, variable, onCallback) {
        var request = new XMLHttpRequest();
        request.addEventListener("load", function(event) {
            onCallback(event.target);
        });
    
        request.open("PUT", path + "/" + variable);
        request.setRequestHeader("Content-Type", "charset=UTF-8");
        request.send();
    },
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
    }
}