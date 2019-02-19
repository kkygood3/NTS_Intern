// Rest API로 서버로부터 해당 url의 json데이터를 가져옴
function requestAjax(callback, url) {
    let ajaxReq = new XMLHttpRequest();
    ajaxReq.callback = callback;
    ajaxReq.addEventListener('load', evt => {
        callback(evt.target.response)
    });
    ajaxReq.open('GET', 'api/' + url);
    ajaxReq.responseType = 'json';
    ajaxReq.send();
}

// Url의 name에 해당하는 Parameter 추출
function getUrlParameter(name) {
    let params = location.href.split('?')[1].split('&');
    for (let i = 0; i < params.length; i++) {
        let paramSplited = params[i].split('=');
        let paramName = paramSplited[0];
        let paramValue = paramSplited[1];

        if (paramName === name) {
            return paramValue;
        }
    }
}

var generateRandom = function (min, max) {
    var ranNum = Math.floor(Math.random() * (max - min + 1)) + min;
    return ranNum;
}

function addCommaInNumber(number) {
    return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

var DateFormmater = function (date) {
    let week = new Array('일', '월', '화', '수', '목', '금', '토');
    return date.getFullYear() + "." + (date.getMonth() + 1) + "." + date.getDate() + ".(" + week[date.getDay()] + ")";
}

var DateObj = {
    currentDate: "",
    lastDate: "",
    getrandomDate: function () {
        let date = new Date();
        /* 타임스탬프 + [0~5] * 1000*60*60*24ms(=[오늘~5]일) */
        date.setTime(date.getTime() + generateRandom(0, 5) * 1000 * 60 * 60 * 24)
        return DateFormmater(date);
    },
    setDate: function () {
        let date = new Date();
        this.currentDate = DateFormmater(date);

        /* 타임스탬프 + 5 * 1000*60*60*24ms(=5일) */
        date.setTime(date.getTime() + 5 * 1000 * 60 * 60 * 24);
        this.lastDate = DateFormmater(date);
    }
}

var mapPriceType = new Map([
    ['A', '성인'],
    ['Y', '청소년'],
    ['B', '어린이'],
    ['S', '세트'],
    ['D', '장애인'],
    ['C', '지역주민'],
    ['E', '얼리버드'],
    ['V', 'VIP'],
    ['R', 'R석'],
    ['D', '평일']
]);

function initDisplayInfo(displayInfoData) {
    // 이전화면 이동
    document.querySelector('.btn_back').setAttribute('href', '/detail?id=' + getUrlParameter('id'));

    let displayProductImages = displayInfoData["productImages"];
    // ma 타입의 이미지 정보를 추가
    displayProductImages.forEach(image => {
        if (image.type === 'MA') {
            document.querySelector('li.item > img').setAttribute('src', image.saveFileName);
        }
    });

    let ImageTextTarget = document.querySelectorAll('.preview_txt_dsc')[1];
    ImageTextTarget.innerText = DateObj.currentDate + "~" + DateObj.lastDate + ", 잔여티켓 2769매";

    // 장소
    let discription = document.querySelectorAll('.dsc');
    discription[0].innerHTML = "기간 : " + DateObj.currentDate + "~" + DateObj.lastDate;

    // 관람시간
    let displayInfo = displayInfoData["displayInfo"];
    discription[1].innerText = displayInfo.openingHours.replace('/-/g', '<br>- ');

    // 요금
    let prices = displayInfoData["productPrices"];
    let priceTarget = discription[2];
    let minPrice = 1000000;
    prices.forEach(price => {
        if (minPrice > price.price) minPrice = price.price;

        priceTarget.innerText += mapPriceType.get(price.priceTypeName);
        priceTarget.innerText += addCommaInNumber(price.price) + '원\r\n';
    });

    document.querySelector('.preview_txt_dsc').innerText = "₩ " + addCommaInNumber(minPrice) + " ~ ";

}

function TicketObj(target, index, price) {
    this.target = target;
    this.index = index;
    this.price = price;

    target[0].classList.add('minusBtn' + index);
    target[1].classList.add('plusBtn' + index);
}

var changePriceEvent = function () {
    document.querySelectorAll('.count_control_input').forEach((ticketItem, index) => {
        let ticketItemTotalPrice = ticketItem.parentElement.parentElement.children[1].children[0];

        ticketItemTotalPrice.innerText = addCommaInNumber(ticketItem.value * ticketItems[index].price);
    });
}

TicketObj.prototype.addMinusClickEvent = function () {
    let index = this.index;
    document.querySelector('.minusBtn' + index).addEventListener('click', function () {
        if (this.parentElement.children[1].value in ["1", "0"]) {
            this.parentElement.querySelector('.ico_minus3').classList.add('disabled');
            this.parentElement.querySelector('.count_control_input').classList.add('disabled');
            this.parentElement.parentElement.querySelector('.individual_price').classList.remove('on_color');
            this.parentElement.children[1].setAttribute('value', "0");
        } else {
            this.parentElement.children[1].setAttribute('value', String(Number(this.parentElement.children[1].value) - 1));
        }
        changePriceEvent();
    });
}

TicketObj.prototype.addPlusClickEvent = function () {
    let index = this.index;
    document.querySelector('.plusBtn' + index).addEventListener('click', function () {
        if (this.parentElement.children[1].value === "0") {
            this.parentElement.querySelector('.ico_minus3').classList.remove('disabled');
            this.parentElement.querySelector('.count_control_input').classList.remove('disabled');
            this.parentElement.parentElement.querySelector('.individual_price').classList.add('on_color');
        }
        this.parentElement.children[1].setAttribute('value', String(Number(this.parentElement.children[1].value) + 1));
        changePriceEvent();
    });
}

var ticketItems = new Object();

async function initTickectBox(productPrices) {
    let ticketTemplate = document.querySelector('#ticketItem').innerText;
    let bindticketTemplate = Handlebars.compile(ticketTemplate);
    let ticketContainer = document.querySelector('div.ticket_body');

    productPrices.forEach((price, index) => {
        price.priceTypeName = mapPriceType.get(productPrices[0].priceTypeName);
        let itemPrice = price.price;
        price.price = addCommaInNumber(itemPrice);
        ticketContainer.innerHTML += bindticketTemplate(price);

        ticketItems[index] = new TicketObj(ticketContainer.lastElementChild.querySelectorAll('.btn_plus_minus'), index, itemPrice);
    });

    productPrices.forEach((price, index) => {
        ticketItems[index].addMinusClickEvent();
        ticketItems[index].addPlusClickEvent();
    });

    document.querySelector('.ticket_body').addEventListener('change', () => {

    })
}

function loadDisplayInfoCallback(displayInfoData) {

    DateObj.setDate();

    initDisplayInfo(displayInfoData);

    let productPrices = displayInfoData["productPrices"];

    initTickectBox(productPrices);

}

// DOMContentLoaded 초기 설정
document.addEventListener('DOMContentLoaded', function () {
    requestAjax(loadDisplayInfoCallback, 'products/' + getUrlParameter('id'));
});