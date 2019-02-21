// Rest API로 서버로부터 해당 url의 json데이터를 가져옴 (GET)
function requestAjax(callback, url) {
    let ajaxReq = new XMLHttpRequest();
    ajaxReq.callback = callback;
    ajaxReq.addEventListener('load', evt => {
        callback(evt.target.response);
    });
    ajaxReq.open('GET', 'api/' + url);
    ajaxReq.responseType = 'json';
    ajaxReq.send();
}

/**
 * date형식을 mysql 타입으로 변환 시켜줌
 */
Date.prototype.toMysqlFormat = function () {
    return this.getUTCFullYear() + "-" + twoDigits(1 + this.getUTCMonth()) + "-" + twoDigits(this.getUTCDate()) + " " + twoDigits(this.getUTCHours()) + ":" + twoDigits(this.getUTCMinutes()) + ":" + twoDigits(this.getUTCSeconds());
};

function twoDigits(d) {
    if (0 <= d && d < 10) return "0" + d.toString();
    if (-10 < d && d < 0) return "-0" + (-1 * d).toString();
    return d.toString();
}

function initDisplayInfo(response) {
    let reservationData = response["reservations"];
    let titles = document.querySelectorAll('.figure');
    let currentDate = (new Date()).toMysqlFormat();

    // 0 : 전체, 1 : 이용예정, 2 : 이용완료, 3 : 취소 및 환불
    let size = response.size;
    let todo = 0;
    let done = 0;
    let cancel = 0;
    reservationData.forEach(data => {
        if (data.cancelYn) {
            cancel++;
            return true;
        }

        if (data.reservationDate > currentDate) {
            todo++;
        } else {
            done++;
        }
    });
    

    titles[0].innerText = size;
    titles[1].innerText = todo;
    titles[2].innerText = done;
    titles[3].innerText = cancel;
}

// DOMContentLoaded 초기 설정
document.addEventListener('DOMContentLoaded', function () {
    let email = document.querySelectorAll('[title="예약확인"]')[0].innerText;

    requestAjax(initDisplayInfo, 'reservations?reservationEmail=' + email);
});