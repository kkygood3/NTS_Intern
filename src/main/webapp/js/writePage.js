const MAX_COMMENT_LENGTH = 400;

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

// Rest API로 url을 서버로 json데이터를 넘김 (POST)
function requestPostAjax(callback, url, param) {
    let ajaxReq = new XMLHttpRequest();
    ajaxReq.callback = callback;
    ajaxReq.addEventListener('load', function (evt) {
        this.callback(evt.target.response);
    });
    ajaxReq.open('POST', url);
    ajaxReq.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
    ajaxReq.responseType = 'json';
    ajaxReq.send(param);
}

// Url의 name에  해당하는 Parameter 추출
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

let targetObj = {
    ratingBox: document.querySelector('.rating'),
    comment: document.querySelector('.review_write_info'),
    text: document.querySelector('.review_textarea'),
    textLength: document.querySelector('.guide_review'),
    imageInput: document.querySelector("#reviewImageFileOpenInput"),
    imageOutput: document.querySelector(".item_thumb"),
    imageBox: document.querySelector('.item'),
    imageCancelBtn: document.querySelector('.spr_book'),
    submitBtn: document.querySelector('.bk_btn'),
    score : document.querySelector('.star_rank');
}

function RatingObj(score) {
    this.score = score;
    this.starList = document.querySelectorAll('.rating_rdo');
}

RatingObj.prototype.fillStar = function () {
    for (let i = 0; i < 5; ++i) {
        if (i <= this.score) {
            this.starList[i].checked = true;
        } else {
            this.starList[i].checked = false;
        }
    }

    document.querySelector('.star_rank').classList.remove('gray_star');
    document.querySelector('.star_rank').innerText = this.score + 1;
}

function initDisplay(productInfo) {
    let displayInfo = productInfo['displayInfo'];
    let title = displayInfo.productDescription;

    document.querySelector('.title').innerText = title;
}

function initRatingBox() {
    let ratingList = new Object();
    for (let i = 0; i < 5; ++i) {
        ratingList[i] = new RatingObj(i);
    }

    targetObj.ratingBox.addEventListener('click', evt => {
        let target = evt.target;

        if (target.classList.contains('rating_rdo')) {
            let index = target.value - 1;
            ratingList[index].fillStar();
        }

    });
}

function initCommentFocus() {
    targetObj.comment.addEventListener('click', () => {
        targetObj.comment.style.visibility = 'hidden';
        targetObj.text.focus();
    });

    targetObj.text.addEventListener('keyup', e => {
        targetObj.textLength.firstElementChild.innerText = targetObj.text.value.length;
        if (targetObj.text.value.length > 400) {
            alert('글자수는 ' + MAX_COMMENT_LENGTH + '개를 넘길 수 없습니다');
            targetObj.text.value = targetObj.text.value.substr(0, MAX_COMMENT_LENGTH);
            targetObj.textLength.firstElementChild.innerText = MAX_COMMENT_LENGTH;
        }
    });

    targetObj.text.addEventListener('blur', () => {
        if (targetObj.text.value.length == 0) {
            targetObj.comment.style.visibility = 'visible';
        }
    });
}

function valideImageType(image) {
    const result = (['image/jpg',
        'image/png'].indexOf(image.type) > -1);
    return result;
}

function initImageBox() {
    targetObj.imageInput.addEventListener('change', evt => {
        const image = evt.target.files[0];
        if (!valideImageType(image)) {
            alert("invalide image file type");
            return;
        }

        //이렇게 넣으면 이미지 정보가 화면에 노출됩니다.
        targetObj.imageBox.style.display = "block";
        targetObj.imageOutput.src = window.URL.createObjectURL(image);
    })

    targetObj.imageCancelBtn.addEventListener('click', () => {
        targetObj.imageInput.value = '';
        targetObj.imageBox.style.display = "none";
    })
}

function postResponseHandler(response) {
    if (!response || response.result != 'OK') {
        alert('등록 중 문제가 발생했습니다.\r\n잠시 후에 다시 시도해주시기 바랍니다.');
        location.href = '/list';
    } else {
        alert('등록 되었습니다.');
        location.href = '/detail?id=' + getUrlParameter('productId');
    }
}

function initSubmitBtn() {
    if(score.innerText != 0 && targetObj.text.value.length >= 5) {
        targetObj.submitBtn.style.display = 'block';
    }
}

// DOMContentLoaded 초기 설정
document.addEventListener('DOMContentLoaded', function () {
    requestAjax(initDisplay, 'products/' + getUrlParameter('productId'));

    initRatingBox();

    initCommentFocus();

    initImageBox();

    targetObj.submitBtn.style.display = 'none';
});