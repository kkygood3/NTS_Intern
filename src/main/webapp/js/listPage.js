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

// Rest API로 param을 서버로 json데이터를 넘김 (POST)
function requestPostAjax(callback, url, param) {
	let ajaxReq = new XMLHttpRequest();
	ajaxReq.callback = callback;
	ajaxReq.addEventListener('load', function(evt) {
		this.callback(evt.target.response);
	});
	ajaxReq.open('POST', url);
	ajaxReq.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
    ajaxReq.responseType = 'json';
	ajaxReq.send(param);
}

/**
 * 입력된 숫자에 3자리수마다 ,를 추가해줌
 */
function addCommaInNumber(number) {
    return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

/**
 * date형식을 mysql 타입으로 변환 시켜줌
 */
Date.prototype.toMysqlFormat = function () {
    return this.getUTCFullYear() + "-" + twoDigits(1 + this.getUTCMonth()) + "-" + twoDigits(this.getUTCDate()) + " " + twoDigits(this.getUTCHours()) + ":" + twoDigits(this.getUTCMinutes()) + ":" + twoDigits(this.getUTCSeconds());
};

/**
 * date형식을 2019.02.21.(목)과 같은 형식의 String 타입으로 변환 시켜줌
 */
var DateFormmater = function (date) {
    let week = new Array('일', '월', '화', '수', '목', '금', '토');
    return date.getFullYear() + "." + (date.getMonth() + 1) + "." + date.getDate() + ".(" + week[date.getDay()] + ")";
}

function twoDigits(d) {
    if (0 <= d && d < 10) return "0" + d.toString();
    if (-10 < d && d < 0) return "-0" + (-1 * d).toString();
    return d.toString();
}

/**
 * Handlebar 처리를 위한 데이터 전처리
 * @param {*} data 
 */
function addExtraData(data) {
    data.categoryName = data['displayInfo'].categoryName;
    data.placeStreet = data['displayInfo'].placeStreet;
    data.productDescription = data['displayInfo'].productDescription;
    data.totalPrice = addCommaInNumber(data.totalPrice);
    data.reservationDate = DateFormmater(new Date(Date.parse(data.reservationDate.replace('-', '/', 'g'))));
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
            let cancelTemplate = document.querySelector('#cancelReservation').innerText;
            let bindcancelTemplate = Handlebars.compile(cancelTemplate);
            let cancelContainer = document.querySelector('li.cancel');

            addExtraData(data);
            cancelContainer.innerHTML += bindcancelTemplate(data);

            cancel++;
            return true;
        }

        if (data.reservationDate > currentDate) {
            let todoTemplate = document.querySelector('#todoReservation').innerText;
            let bindTodoTemplate = Handlebars.compile(todoTemplate);
            let todoContainer = document.querySelector('li.confirmed');

            addExtraData(data);
            todoContainer.innerHTML += bindTodoTemplate(data);

            todo++;
        } else {
            let doneTemplate = document.querySelector('#doneReservation').innerText;
            let bindDoneTemplate = Handlebars.compile(doneTemplate);
            let doneContainer = document.querySelector('li.used');

            addExtraData(data);
            doneContainer.innerHTML += bindDoneTemplate(data);

            done++;
        }
    });


    titles[0].innerText = size;
    titles[1].innerText = todo;
    titles[2].innerText = done;
    titles[3].innerText = cancel;
}

function postResponseHandler(response){
	if(!response || response.result != 'OK'){
		alert('예약 중 문제가 발생했습니다.\r\n잠시 후에 다시 시도해주시기 바랍니다.');
	} else {
		alert('예약이 취소 되었습니다.');
		location.href = '/list';
	}
}

function ReserveParam(reservationInfoId, reservationEmail){
    this.reservationInfoId = parseInt(reservationInfoId);
    this.reservationEmail = reservationEmail;
}

function initCancelBtn() {
    let popupTarget = document.querySelector('.popup_booking_wrapper');

    document.querySelector('li.confirmed').addEventListener('click', function(evt) {
        let target = evt.target;
        
        if(target.tagName === 'span') target = target.parentElement;
        if(target.className === 'btn') {
            popupTarget.style.display = 'block';
            let id = target.parentElement.parentElement.parentElement.querySelector('.reserveId').innerText;
            let title = target.parentElement.parentElement.parentElement.querySelector('.tit').innerText;
            let date = target.parentElement.parentElement.parentElement.querySelector('.item_dsc').innerText;
            let email = document.querySelectorAll('.viewReservation')[1].innerText;

            document.querySelector('.pop_tit').children[0].innerText = title;
            document.querySelector('.pop_tit').children[1].innerText = date;

            document.querySelector('.btn_green').addEventListener('click', function() {
                let reserveRequest = JSON.stringify(new ReserveParam(id, email));
                requestPostAjax(postResponseHandler, 'api/update', reserveRequest);
            })
        }
    });

    document.querySelector('.popup_btn_close').addEventListener('click', function() {
        popupTarget.style.display = 'none';
    });

    document.querySelector('.btn_gray').addEventListener('click', function() {
        popupTarget.style.display = 'none';
    });
}

// DOMContentLoaded 초기 설정
document.addEventListener('DOMContentLoaded', function () {
    let email = document.querySelectorAll('[title="예약확인"]')[0].innerText;

    requestAjax(initDisplayInfo, 'reservations?reservationEmail=' + email);

    initCancelBtn();
});