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
    }
}